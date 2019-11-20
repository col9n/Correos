package sample.clases;

import sample.logica.Logica;

import javax.mail.Store;

public class CuentaCorreo {
    private String user;
    private String pass;
    private Store store;

    public CuentaCorreo(String user, String pass,Store store) {
        this.user = user;
        this.pass = pass;
        this.store = store;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
