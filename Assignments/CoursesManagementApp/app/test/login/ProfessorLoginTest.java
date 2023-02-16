package app.test.login;

import app.src.login.ProfessorLogin;
import app.src.entities.RegistrationToken;

import persistence.CustomGatewayImplementation;

import jspec.*;

public class ProfessorLoginTest extends SpecModule {
    public void spec_code() {
        describe("professor login object", () -> {
            it("creates a new login object with a registration token and an accounts db", () -> {
                ProfessorLogin log = new ProfessorLogin(
                    new RegistrationToken("Oblivious", "one two three four"),
                    new CustomGatewayImplementation("Accounts")
                );

                assert_that(log).isnot(null);
            });

            it("sucessfully logins with an existing registration token", () -> {
                CustomGatewayImplementation accounts_db = new CustomGatewayImplementation("Accounts");
                accounts_db.save(new RegistrationToken("Oblivious", "one two three four"));
                accounts_db.save(new RegistrationToken("Second", "one two three four"));
                accounts_db.save(new RegistrationToken("Third", "one two three four"));

                ProfessorLogin log = new ProfessorLogin(
                    new RegistrationToken("Oblivious", "one two three four"),
                    accounts_db
                );

                assert_that(log.login()).is(true);
            });

            it("fails to login with a non existing registration token", () -> {
                ProfessorLogin log = new ProfessorLogin(
                    new RegistrationToken("OOOOblivious", "one two three four"),
                    new CustomGatewayImplementation("Accounts")
                );

                assert_that(log.login()).is(false);
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
