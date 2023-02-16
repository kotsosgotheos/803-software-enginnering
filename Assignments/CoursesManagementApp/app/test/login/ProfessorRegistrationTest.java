package app.test.login;

import app.src.entities.*;
import app.src.login.ProfessorRegistration;
import persistence.CustomGatewayImplementation;

import jspec.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ProfessorRegistrationTest extends SpecModule {
    private ProfessorRegistration reg = null;

    private String sha512hash(String password) {
        try {
            MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            byte md[] = sha512.digest(password.getBytes());
            String hash = new BigInteger(1, md).toString(16);

            while(hash.length() < 32)
                hash += "0";

            return hash;
        }
        catch(NoSuchAlgorithmException e) {
            return "";
        }
    }

    public void spec_code() {
        describe("Professor Registration", () -> {
            it("creates a new registration object", () -> {
                String password = "123abc";
                this.reg = new ProfessorRegistration(
                    new RegistrationToken("Oblivious", password),
                    new CustomGatewayImplementation("Accounts")
                );

                assert_that(this.reg).isnot(null);
            });

            xit("checks if the password has at least 4 words with size at least 10", () -> {
                String password = "one two three four";
                this.reg = new ProfessorRegistration(
                    new RegistrationToken("Oblivious", password),
                    new CustomGatewayImplementation("Accounts")
                );

                assert_that(this.reg.password_is_safe()).is(true);
            });

            xit("fails if password has less that 4 words", () -> {
                String password = "word1 word2 word3";
                this.reg = new ProfessorRegistration(
                    new RegistrationToken("Oblivious", password),
                    new CustomGatewayImplementation("Accounts")
                );

                assert_that(this.reg.password_is_safe()).is(false);
            });

            xit("fails if password has less that 10 characters", () -> {
                String password = "a b c d e";
                this.reg = new ProfessorRegistration(
                    new RegistrationToken("Oblivious", password),
                    new CustomGatewayImplementation("Accounts")
                );

                assert_that(this.reg.password_is_safe()).is(false);
            });

            it("fails if username already exists", () -> {
                String username = "Oblivious";
                String password = "one two three four";
                
                RegistrationToken reg_token1 = new RegistrationToken("Oblivious", "asg sagwqg qg gqqw");
                RegistrationToken reg_token2 = new RegistrationToken("Another", "asg sagwqg qg gqqw");
                RegistrationToken reg_token3 = new RegistrationToken("Test", "asg sagwqg qg gqqw");
                
                RegistrationToken current = new RegistrationToken(username, password);

                PersistenceGateway accounts_db = new CustomGatewayImplementation("Accounts");
                accounts_db.save(reg_token1);
                accounts_db.save(reg_token2);
                accounts_db.save(reg_token3);

                this.reg = new ProfessorRegistration(current, accounts_db);
                assert_that(this.reg.username_is_safe()).is(false);
            });

            it("succeeds if username is unique", () -> {
                String username = "some unique username";
                String password = "one two three four";

                PersistenceGateway accounts_db = new CustomGatewayImplementation("Accounts");
                RegistrationToken current = new RegistrationToken(username, password);
                this.reg = new ProfessorRegistration(current, accounts_db);

                assert_that(this.reg.username_is_safe()).is(true);
            });

            it("saves a registration token in the db as a login token", () -> {
                String username = "another unique username";
                String password = "one two three four";
                PersistenceGateway accounts_db = new CustomGatewayImplementation("Accounts");
                RegistrationToken current = new RegistrationToken(username, password);
                this.reg = new ProfessorRegistration(current, accounts_db);

                assert_that(this.reg.register()).is(true);
            });

            it("tries to save a token twice but only succeeds the first time", () -> {
                String username = "3rd unique username";
                String password = "one two three four";
                PersistenceGateway accounts_db = new CustomGatewayImplementation("Accounts");
                RegistrationToken current = new RegistrationToken(username, password);
                this.reg = new ProfessorRegistration(current, accounts_db);

                assert_that(this.reg.register()).is(true);
                assert_that(this.reg.register()).is(false);
                assert_that(this.reg.register()).is(false);
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
