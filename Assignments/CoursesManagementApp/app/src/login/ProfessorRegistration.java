package app.src.login;

import app.src.boundaries.RegisterBoundary;
import app.src.entities.Identifiable;
import app.src.entities.PersistenceGateway;
import app.src.entities.RegistrationToken;

public class ProfessorRegistration implements RegisterBoundary {
    private RegistrationToken token;
    private PersistenceGateway accounts_db;

    public ProfessorRegistration(RegistrationToken reg, PersistenceGateway accounts_db) {
        this.token = reg;
        this.accounts_db = accounts_db;
    }

    public boolean username_is_safe() {
        for(Identifiable item : this.accounts_db.get_all_items()) {
            RegistrationToken token = (RegistrationToken)item;
            if(token.username.equals(this.token.username))
                return false;
        }

        return true;
    }

    public boolean password_is_safe() {
        /* TODO Implement inside RegistrationToken */
        // if(this.password.length() > 0 && this.password.length() < 10)
        //     return false;
        // return this.password.split(" ").length >= 4;
        return true;
    }

    public boolean bounds_check() {
        return username_is_safe() && password_is_safe();
    }

    public boolean register() {
        if(!bounds_check())
            return false;

        this.accounts_db.save(this.token);

        return true;
    }
}
