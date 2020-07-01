package com.latihan2.latihan2.config;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JWTFilter extends GenericFilterBean {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORITIES_KEY = "role";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Authorization header.");
        } else {
            try {
                String token = authHeader.substring(7);
                Claims claims = Jwts.parser().setSigningKey("6v9y$B&E").parseClaimsJws(token).getBody();
//                System.out.println("token claims : " + claims);
                request.setAttribute("claims", claims);
                SecurityContextHolder.getContext().setAuthentication(getAuthentication(claims));
                filterChain.doFilter(req, res);
            } catch (SignatureException e) {
//                System.out.println("token : invalid");
                ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            } catch (UnsupportedJwtException exception) {
//                System.out.println("token : unsuported");
                ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, "token unsuported");
            } catch (MalformedJwtException exception) {
//                System.out.println("token : invalid malformed");
                ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, "invalid token");
            } catch (IllegalArgumentException exception) {
//                System.out.println("token : ilegal");
                ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
            } catch (ExpiredJwtException e) {
//                System.out.println("token : expired");
                ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, "token expired");
            }
        }
    }


    public Authentication getAuthentication(Claims claims) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        System.out.println("claims : " + claims.get(AUTHORITIES_KEY));
        List<String> roles = (List<String>) claims.get(AUTHORITIES_KEY);

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        User principal = new User(claims.getSubject(), "", authorities);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(principal, "", authorities);
        return usernamePasswordAuthenticationToken;
    }
}