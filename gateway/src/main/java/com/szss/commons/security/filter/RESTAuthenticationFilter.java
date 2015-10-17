package com.szss.commons.security.filter;


import com.szss.commons.security.tokens.RESTAuthenticationToken;
import com.szss.commons.security.tokens.RESTCredentials;
import com.szss.commons.security.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RESTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger log = LoggerFactory.getLogger(RESTAuthenticationFilter.class);

    /**
     * @param defaultFilterProcessesUrl the default value for <tt>filterProcessesUrl</tt>.
     */
    protected RESTAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String apiKeyValue = obtainAPIKeyValue(request);
        String requestSaltValue = obtainRequestSaltValue(request);
        String timestampValue = obtainTimestampValue(request);
        String hashedSecureValue = obtainHashedSecureValue(request);
        String apiMethod=obtainApiMethodValue(request);
        String httpMethod = request.getMethod();
        String requestURI = request.getRequestURI();

        log.info("apiKeyValue : {}", apiKeyValue);
        log.info("apiMethod : {}", apiMethod);
        log.info("requestSaltValue : {}", requestSaltValue);
        log.info("hashedSecureValue : {}", hashedSecureValue);
        log.info("timestamp : {}", timestampValue);
        log.info("httpMethod : {}", httpMethod);
        log.info("requestURI : {}", requestURI);

        AbstractAuthenticationToken authRequest = createAuthenticationToken(apiKeyValue, new RESTCredentials(requestSaltValue, hashedSecureValue), timestampValue, httpMethod, requestURI,apiMethod);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }

    private String decodeParameterValue(HttpServletRequest request, String requestParameterName) throws UnsupportedEncodingException {
        //This is basically to avoid the weird RFC spec when it comes to spaces in the URL and how they are encoded
        /*
        return URLDecoder.decode(getParameterValue(request, requestParameterName), request.getCharacterEncoding())
                .replaceAll(" ", "+");
        */
        return getParameterValue(request, requestParameterName);
    }

    private String getHeaderValue(HttpServletRequest request, String headerParameterName) {
        return (request.getHeader(headerParameterName) != null) ? request.getHeader(headerParameterName) : "";
    }

    private String getParameterValue(HttpServletRequest request, String requestParameterName) {
        return (request.getParameter(requestParameterName) != null) ? request.getParameter(requestParameterName) : "";
    }

//    private String obtainRequestSaltValue(HttpServletRequest request) throws UnsupportedEncodingException {
//        return getHeaderValue(request, Constants.REQUEST_SALT_PARAMETER_NAME);
//    }
//
//    private String obtainAPIKeyValue(HttpServletRequest request) throws UnsupportedEncodingException {
//        return getHeaderValue(request, Constants.API_KEY_PARAMETER_NAME);
//    }
//
//    private String obtainTimestampValue(HttpServletRequest request) throws UnsupportedEncodingException {
//        return getHeaderValue(request, Constants.REQUEST_TIMESTAMP_PARAMETER_NAME);
//    }
//
//    private String obtainHashedSecureValue(HttpServletRequest request) throws UnsupportedEncodingException {
//        return getHeaderValue(request, Constants.SECURE_HASH_PARAMETER_NAME);
//    }

    private String obtainRequestSaltValue(HttpServletRequest request) throws UnsupportedEncodingException {
        return getParameterValue(request, Constants.REQUEST_SALT_PARAMETER_NAME);
    }

    private String obtainAPIKeyValue(HttpServletRequest request) throws UnsupportedEncodingException {
        return getParameterValue(request, Constants.API_KEY_PARAMETER_NAME);
    }

    private String obtainTimestampValue(HttpServletRequest request) throws UnsupportedEncodingException {
        return getParameterValue(request, Constants.REQUEST_TIMESTAMP_PARAMETER_NAME);
    }

    private String obtainHashedSecureValue(HttpServletRequest request) throws UnsupportedEncodingException {
        return getParameterValue(request, Constants.SECURE_HASH_PARAMETER_NAME);
    }

    private String obtainApiMethodValue(HttpServletRequest request) throws UnsupportedEncodingException {
        return getParameterValue(request, Constants.REQUEST_METHOD_PARAMETER_NAME);
    }

    /**
     * Provided so that subclasses may configure what is put into the authentication request's details
     * property.
     *
     * @param request     that an authentication request is being created for
     * @param authRequest the authentication request object that should have its details set
     */
    protected void setDetails(HttpServletRequest request, AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private AbstractAuthenticationToken createAuthenticationToken(String apiKeyValue, RESTCredentials restCredentials, String timestamp, String httpMethod, String requestURI,String apiMethod) {
        return new RESTAuthenticationToken(apiKeyValue, restCredentials, timestamp, httpMethod, requestURI,apiMethod);
    }

    @Override
    /**
     * Because we require the API client to send credentials with every request, we must authenticate on every request
     */
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }
}
