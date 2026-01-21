package com.next.routeMaster.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String authHeader = request.getHeader("Authorization");
            String username = null;
            String jwtToken = null;

            // Check if Authorization header exists and starts with "Bearer "
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                jwtToken = authHeader.substring(7);

                // Only try to extract username if token is not empty
                if (jwtToken != null && !jwtToken.trim().isEmpty()) {
                    try {
                        username = jwtUtil.getUsernameFromToken(jwtToken);
                    } catch (Exception e) {
                        // Log the error but continue the filter chain
                        logger.error("Error extracting username from JWT token: " + e.getMessage());
                    }
                }
            }

            // If we have a valid username and no authentication is set
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Validate the token
                if (jwtUtil.validateToken(jwtToken)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

        } catch (Exception e) {
            // Catch any unexpected errors to prevent breaking the filter chain
            logger.error("Cannot set user authentication: " + e.getMessage());
        }

        // Always continue the filter chain
        filterChain.doFilter(request, response);
    }
}