package com.szss.commons.security.repository;
/**
 * @author Dhrubo
 *
 */

import org.springframework.security.core.userdetails.UserDetails;

public interface UserSecurityRepository {

    UserDetails getUserByUsername(String username);

    UserDetails getUserByApiKey(String apiKey);
}
