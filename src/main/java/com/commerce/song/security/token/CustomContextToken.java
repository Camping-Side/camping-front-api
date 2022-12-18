package com.commerce.song.security.token;

import com.commerce.song.security.domain.UserContextDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomContextToken extends UsernamePasswordAuthenticationToken {
    public CustomContextToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
    public static CustomContextToken getTokenFromAccountContext(UserContextDto userDto) {
        return new CustomContextToken(userDto, userDto.getPassword(), userDto.getAuthorities());
    }
}
