package by.exadel.internship.config.jwt;

import by.exadel.internship.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@Component
@RequiredArgsConstructor
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private final UserDetailsServiceImpl userDetailsService;

    private Optional<String> parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(AUTHORIZATION);
        if (!StringUtils.isEmpty(headerAuth) && headerAuth.startsWith(BEARER)) {
            return Optional.of(StringUtils.substring(headerAuth, 7));
        }
        return Optional.empty();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            parseJwt(httpServletRequest)
                    .filter(jwtService::validateJwtToken)
                    .ifPresent(jwt -> {
                        String username = jwtService.getUserNameFromJwtToken(jwt);

                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    });
        } catch (Exception e) {
            MDC.put("class: ", AuthTokenFilter.class.getSimpleName());
            log.error("Authentication Error: " + e.getMessage());
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}