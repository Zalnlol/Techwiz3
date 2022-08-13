package fpt.aptech.hss.Model;

import java.io.Serializable;

public class AccountToken implements Serializable {
    private String token;
    private Account id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Account getId() {
        return id;
    }

    public void setId(Account id) {
        this.id = id;
    }
}
