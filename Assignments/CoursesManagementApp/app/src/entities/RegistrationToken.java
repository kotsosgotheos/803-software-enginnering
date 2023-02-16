package app.src.entities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import persistence.SqliteGatewayImplementation;

public class RegistrationToken extends Identifiable {
    public String username;
    public String password;

    /* TODO Inject this */
    private transient PersistenceGateway courses_db = null;

    private String hash_string(String str) {
        try {
            MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            byte md[] = sha512.digest(str.getBytes());
            String hash = new BigInteger(1, md).toString(16);

            while(hash.length() < 32)
                hash += "0";

            return hash;
        }
        catch(NoSuchAlgorithmException e) {
            return "";
        }
    }

    public RegistrationToken(String username, String password) {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
        this.username = username;
        this.password = hash_string(password);
        this.courses_db = new SqliteGatewayImplementation(this.username + "_courses");
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public PersistenceGateway get_courses_db() {
        if(this.courses_db == null)
            this.courses_db = new SqliteGatewayImplementation(this.username + "_courses");
        return this.courses_db;
    }

    public boolean equals(RegistrationToken other) {
        return this.username.equals(other.username)
            && this.password.equals(other.password);
    }

    @Override
    public boolean is(Identifiable o) {
        RegistrationToken other = (RegistrationToken)o;
        return this.username.equals(other.username)
            && this.password.equals(other.password);
    }

    @Override
    public String toString() {
        return "";
    }
}
