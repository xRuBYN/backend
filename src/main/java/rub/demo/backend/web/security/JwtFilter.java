package rub.demo.backend.web.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import rub.demo.backend.domain.model.User;
import rub.demo.backend.web.security.model.CustomUserDetails;
import rub.demo.backend.web.security.service.CustomUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static io.jsonwebtoken.lang.Strings.hasText;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION = "Authorization";

    private final JwtProvider jwtProvider;

    private final CustomUserDetailsService service;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Checking if the user have the rights to access.");

        String token = getTokenFromRequest((HttpServletRequest) servletRequest);

        if (token != null && jwtProvider.validateToken(token)) {
            Authentication authentication = getAuthentication(token);

            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }


    public void doFilterForProduct(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null && jwtProvider.validateToken(token)) {
            Authentication authentication = getAuthentication(token);
            if (authentication != null) {

            }
        }


    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        return (hasText(bearer) && bearer.startsWith("Bearer ")) ? bearer.substring(7) : null;
    }

    public Authentication getAuthentication(String token) {
        CustomUserDetails userDetails = this.service.loadUserByUsername(jwtProvider.getEmailFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, jwtProvider.getEmailFromToken(token), userDetails.getAuthorities());
    }

}
