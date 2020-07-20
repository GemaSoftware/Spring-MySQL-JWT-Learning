package com.gemasoftware.springjwtmysql.security.jwt;

import com.gemasoftware.springjwtmysql.security.services.UserDetailsServiceImp;
import com.gemasoftware.springjwtmysql.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationJWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserDetailsServiceImp userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwtFromRequest = parseJWT(httpServletRequest);

            //tries to validate the jwt token recived using utility class and makes sure it is not null
            if(jwtFromRequest != null && jwtUtility.validateJwtToken(jwtFromRequest)){

                //gets username from JWT token sent to server.
                String username = jwtUtility.getUsernameFromJwtToken(jwtFromRequest);

                //Trys to find that username from the userService
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                //Creates a user/pass auth token and attaches it to the securitycontextholder.
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            } catch (Exception e){
            //otherwise,error authenticating.
            logger.error("Error setting up user authentication", e);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String parseJWT(HttpServletRequest req){
        String authHeader = req.getHeader("Authorization");

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7, authHeader.length());
        }
        return null;
    }

}
