package com.microservice.comprasycotizaciones.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String alias;

    public CustomAuthenticationToken(String principal, String alias, Collection<? extends GrantedAuthority> authorities) {
        super(principal, null, authorities);
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
