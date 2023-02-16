package app.test.entities;

import app.src.entities.RegistrationToken;
import jspec.*;

public class RegistrationTokenTest extends SpecModule {
    public void spec_code() {
        describe("LoginToken", () -> {
            it("creates a login token with initial parameters", () -> {
                RegistrationToken token = new RegistrationToken("oblivious", "123456");
                assert_that(token).isnot(null);
            });

            it("checks for equality for 2 login tokens", () -> {
                RegistrationToken t1 = new RegistrationToken("batman", "1234");
                RegistrationToken t2 = new RegistrationToken("batman", "1234");

                assert_that(t1.equals(t2)).is(true);
            });

            it("checks for inequality for 2 login token", () -> {
                RegistrationToken t1 = new RegistrationToken("batman", "1234");
                RegistrationToken t2 = new RegistrationToken("batman", "8521");

                assert_that(t1.equals(t2)).is(false);
            });

            it("gets the username", () -> {
                RegistrationToken token = new RegistrationToken("user", "pass");
                assert_that(token.username).is("user");
            });
            
            it("gets the password", () -> {
                RegistrationToken token = new RegistrationToken("user", "pass");
                assert_that(token.password).is("5b722b307fce6c944905d132691d5e4a2214b7fe92b738920eb3fce3a90420a19511c3010a0e7712b054daef5b57bad59ecbd93b3280f210578f547f4aed4d25");
            });

            after(() -> {
                new java.io.File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
