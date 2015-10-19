package com.szss.gateway;

import java.io.Serializable;

/**
 * Created by zcg on 15/10/19.
 */
public class User implements Serializable {
    private String name;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
