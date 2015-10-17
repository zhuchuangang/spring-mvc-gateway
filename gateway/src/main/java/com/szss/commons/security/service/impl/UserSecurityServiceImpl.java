package com.szss.commons.security.service.impl;

import com.szss.commons.security.repository.AppInfo;
import com.szss.commons.security.repository.UserInfo;
import com.szss.commons.security.repository.UserInfoDetails;
import com.szss.commons.security.service.UserSecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by zcg on 15/10/6.
 */
@Service
public class UserSecurityServiceImpl implements UserSecurityService {

    @Override
    public UserDetails getUserByApiKey(String apiKey) {
        AppInfo info = new AppInfo();
        info.setAppkey("appkey");
        info.setAppsecret("123");
        info.setDeleted(false);
        info.setLocked(false);
        if (info == null) {
            throw new UsernameNotFoundException("The appkey is not found,this appkey is " + apiKey);
        }
        UserInfoDetails details = new UserInfoDetails(info.getAppkey(), info.getAppsecret(), info.getLocked(), info.getDeleted());
        return details;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo info = new UserInfo();
        info.setUsername("zhu");
        info.setPassword("123");
        info.setDeleted(false);
        info.setLocked(false);
        if (info == null) {
            throw new UsernameNotFoundException("The username is not found,this username is " + username);
        }
        UserInfoDetails details = new UserInfoDetails(info.getUsername(), info.getPassword(), info.getLocked(), info.getDeleted());
        return details;
    }
}
