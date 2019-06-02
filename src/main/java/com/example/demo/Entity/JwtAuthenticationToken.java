package com.example.demo.Entity;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.stereotype.Component;

/**
 * Created by forget on 2019/3/18.
 */
@Component
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    private final Object principal;
    private Object credentials;
    private Object details;

    @Override
    public Object getDetails() {
        return details;
    }

    @Override
    public void setDetails(Object details) {
        this.details = details;
    }

    private String JwtToken;

    public JwtAuthenticationToken() {
        super(null);
        this.principal = null;
    }

    public JwtAuthenticationToken(String username) {
        super(null);
        this.principal = username;
    }

    public String getJwtToken() {
        return JwtToken;
    }

    public void setJwtToken(String jwtToken) {
        JwtToken = jwtToken;
    }

    public void setCredentials(Object credentials) {
        this.credentials = credentials;

    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
