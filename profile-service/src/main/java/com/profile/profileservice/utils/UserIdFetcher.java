package com.profile.profileservice.utils;

import com.profile.profileservice.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
@Component
public class UserIdFetcher {

   public String getUserId(HttpServletRequest httpServletRequest) {
       String authorizationHeader = httpServletRequest.getHeader("Authorization");
       String token = null;
       String secret="taeganger";
       if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
           token = authorizationHeader.substring(7);

           try {
               Claims body = Jwts.parser()
                       .setSigningKey(secret)
                       .parseClaimsJws(token)
                       .getBody();
               String userId=(String) body.get("sub");
               return userId;
           } catch (JwtException | ClassCastException e) {
               return "JWT exception";
           }
       }

       return "sorry";
   }
}
