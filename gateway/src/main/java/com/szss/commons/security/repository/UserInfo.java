package com.szss.commons.security.repository;

import java.io.Serializable;

/**
 * Created by zcg on 15/10/6.
 */
public class UserInfo implements Serializable {

    private String username;

    private String password;

    private Boolean locked;

    private Boolean deleted;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
