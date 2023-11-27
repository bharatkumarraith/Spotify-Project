package com.example.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;

        String authHeader = httpServletRequest.getHeader("Authorization");

        if (authHeader==null || !authHeader.startsWith("Bearer")){
            //The request header does not contain any authorization token
            //We can process the exception if anything or errorneous code
            throw new ServletException("Token is Missing");
        }else {
            //The token is contained by the header so we need to process it.
            //"Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbElkIjoiZ2FuZXNoQEdtYWlsLmNvbSIsImlhdCI6MTY4NzczMDk4N30.01c4suNuKRn8nHVf22ZKG974xK93WeIUH_
            // MYkPO0Q0S3mZJPl8O-IDGBmYjuIVOhvyXRQNH2bdZwjEMs7OMlvQ"

            System.out.println("Authorization key:" +authHeader);

            String token = authHeader.substring(7);

            Claims claims = Jwts.parser().setSigningKey("myKey").parseClaimsJws(token).getBody();

            System.out.println("Claims From Token " + claims);

            httpServletRequest.setAttribute("email",claims.get("email"));

            httpServletRequest.setAttribute("user_name",claims.get("user_name"));

            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}









