package com.szss.commons.security.repository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by zcg on 15/10/6.
 */
public class UserInfoDetails implements UserDetails {

    private String username;

    private String password;

    private Boolean locked;

    private Boolean deleted;

    public UserInfoDetails(String username, String password, Boolean locked, Boolean deleted) {
        this.username = username;
        this.password = password;
        this.locked = locked;
        this.deleted = deleted;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !deleted;
    }
}
