/**
 *
 */
package com.szss.commons.security.tokens;

/**
 * @author Dhrubo
 *
 */

import com.szss.commons.security.utils.Constants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RESTAuthenticationToken extends UsernamePasswordAuthenticationToken {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String timestamp;
    private String httpMethod;
    private String requestURI;
    private String apiMethod;

    public RESTAuthenticationToken(Object principal, Object credentials, String timestamp, String httpMethod, String requestURI,String apiMethod) {
        super(principal, credentials);
        this.timestamp = timestamp;
        this.httpMethod = httpMethod;
        this.requestURI = requestURI;
        this.apiMethod=apiMethod;
    }

    public RESTAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public RESTAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getApiMethod() {
        return apiMethod;
    }

    public Map<String, String> getParam() {
        Map<String, String> param = new HashMap<String, String>();
        param.put(Constants.REQUEST_HTTP_METHOD_PARAMETER_NAME, httpMethod);
        param.put(Constants.REQUEST_URI_PARAMETER_NAME, requestURI);
        param.put(Constants.REQUEST_TIMESTAMP_PARAMETER_NAME, timestamp);
        param.put(Constants.REQUEST_METHOD_PARAMETER_NAME,apiMethod);
        param.put(Constants.API_KEY_PARAMETER_NAME, getPrincipal().toString());
        return param;
    }
}
