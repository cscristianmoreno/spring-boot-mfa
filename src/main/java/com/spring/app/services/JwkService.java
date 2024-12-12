package com.spring.app.services;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import org.springframework.stereotype.Service;

import com.spring.app.models.services.IJwkService;

@Service
public class JwkService implements IJwkService {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;

    @Value("${jwk.secret.issuer}")
    private String secretIssuer;

    @Value("${jwk.expire}")
    private long expire;

    @Override
    public String getToken(Authentication authentication) {
        String name = authentication.getName();

        List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet
        .builder()
            .subject(name)
            .issuer(secretIssuer)
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expire))
            .claim("scope", roles)
        .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public Map<String, Object> getClaims(String token) {
        return jwtDecoder.decode(token).getClaims();
    }
}
