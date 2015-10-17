package com.szss.commons.security.repository;

import java.io.Serializable;

/**
 * Created by zcg on 15/10/6.
 */
public class AppInfo implements Serializable {

    private String appkey;

    private String appsecret;

    private Boolean locked;

    private Boolean deleted;


    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
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
