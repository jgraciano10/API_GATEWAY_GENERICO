package com.testApp.demo.Service;

import com.testApp.demo.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    private String secretKey= "asfsdfdnfbdbczjdkncjdnjdnjvnzñcz44646zd161sdcshcasgx";
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(userDetails.getUsername())
                .issuer("DCB")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60*10*1000))
                .and()
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey(){
        byte[] decode
                =Decoders.BASE64.decode(getSecretKey());
        return Keys.hmacShaKeyFor(decode);
    }
    private String getSecretKey(){
        return "kHVoYggQYWCI8S30i5fc2jlHCcAoII3MUrR9DSlRRy5Uzq1nO53DwlS7ccRLCyqv";

    }

    public String extractUserName(String jwt) {
        return extractClaims(jwt, Claims::getSubject);
    }
     public Date extractExpiration(String jwt) {
         return extractClaims(jwt, Claims::getExpiration);
     }

    private <T>T extractClaims(String jwt, Function<Claims, T> claimResolver) {
        Claims claims = extractClaims(jwt);
        return claimResolver.apply(claims);
    }

    private Claims extractClaims(String jwt) {
        return Jwts
                .parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String userName = extractUserName(jwt);

        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }
}
