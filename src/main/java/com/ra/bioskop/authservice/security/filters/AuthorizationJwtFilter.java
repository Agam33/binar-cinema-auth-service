package com.ra.bioskop.authservice.security.filters;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ra.bioskop.authservice.util.Constants;
import com.ra.bioskop.authservice.util.JwtUtil;

@Component
public class AuthorizationJwtFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationJwtFilter.class);

    private final JwtUtil jwtUtil;

    private final UserDetailsService userDetailService;

    public AuthorizationJwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailService) {
        this.jwtUtil = jwtUtil;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        if (!hasToken(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = getToken(request);

        if (!jwtUtil.validateJwtToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        setAuthentication(token, request);

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String token, HttpServletRequest request) {
        String email = jwtUtil.getUserNameFromJwtToken(token);
        LOGGER.info("Email - " + email);
        UserDetails userDetails = userDetailService.loadUserByUsername(email);
        request.setAttribute("token", token);
        request.setAttribute("authorities", authoritiesToString(userDetails.getAuthorities()));
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader(Constants.HEADER);
        return header.split(" ")[1].trim();
    }

    private boolean hasToken(HttpServletRequest request) {
        String header = request.getHeader(Constants.HEADER);
        return !ObjectUtils.isEmpty(header) && header.startsWith(Constants.TOKEN_PREFIX);
    }

    private String authoritiesToString(Collection<? extends GrantedAuthority> collection) {
        StringBuilder strb = new StringBuilder();
        for(GrantedAuthority a : collection) {
            strb.append(a.getAuthority());
            strb.append(" ");
        }
        return strb.toString().trim();
    }
}
