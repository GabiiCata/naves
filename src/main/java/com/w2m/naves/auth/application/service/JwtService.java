package com.w2m.naves.auth.application.service;

import com.w2m.naves.utils.JwtTokenUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final JwtTokenUtil jwtTokenUtil;

    public JwtService(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String extractUsername(String token) {
        return jwtTokenUtil.getUsernameFromToken(token);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return jwtTokenUtil.validateToken(token, userDetails);
    }
}
