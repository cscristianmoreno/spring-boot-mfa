package com.spring.app.security;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.app.dto.ResponseEntityDTO;
import com.spring.app.services.JwkService;
import com.spring.app.utils.AuthenticationContextUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomOncePerRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwkService jwkService;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${auth.secret.authorization}")
    private String secretAuthorization;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();

        if (uri.contains("/auth/") || uri.equals("/users/save")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (uri.equals("/actuator/health") || uri.equals("/server/turn-off")) {
            if (!verifySecretKey(request)) {
                unauthorize(response, "You don't have access");
                return;
            }
            
            filterChain.doFilter(request, response);
            return;
        }

        String authorization = request.getHeader("Authorization");
        
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            unauthorize(response, "Authorization is required");
            return;
        }

        try {
            String token = authorization.split(" ")[1];

            Map<String, Object> claims = jwkService.getClaims(token);
            String username = (String) claims.get("sub");

            
            @SuppressWarnings("unchecked")
            List<String> authorities = (List<String>) claims.get("scope");

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                username,
                null,
                AuthorityUtils.createAuthorityList(authorities)
            );

            AuthenticationContextUtil.setAuthenticationContext(authentication);
            
            authentication = AuthenticationContextUtil.getAuthenticationContext();
            System.out.println(authentication);

            filterChain.doFilter(request, response);
        }
        catch (JwtException exception) {
            unauthorize(response, exception.getMessage());
        }

    }
    
    private void unauthorize(final HttpServletResponse response, String message) throws IOException {
        ResponseEntityDTO responseEntityDTO = new ResponseEntityDTO();
        responseEntityDTO.setStatus(HttpStatus.UNAUTHORIZED);
        responseEntityDTO.setValue(message);

        String convertJson = objectMapper.writeValueAsString(responseEntityDTO);

        response.getWriter().write(convertJson);
        response.setStatus(responseEntityDTO.getStatus().value());
    }

    private boolean verifySecretKey(final HttpServletRequest request) {
        String secretKey = request.getHeader("X-Authorization");
        return (secretKey != null && secretKey.equals(secretAuthorization));
    }
}
