package ru.TagAll.tenderHackBack.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;
import ru.TagAll.tenderHackBack.application.customer.domain.CustomerRepository;
import ru.TagAll.tenderHackBack.utils.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;


@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION = "Authorization";

    private final JwtUtils jwtUtils;

    private final CustomerRepository customerRepository;

    @Override
    public void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                 @NonNull FilterChain filterChain)
            throws IOException, ServletException {
        String token = getTokenFromRequest(request);
        if (token != null && jwtUtils.validateToken(token)) {
            Customer customer = customerRepository.getCustomerByEmail(jwtUtils.getWordForToken(token));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customer,
                    null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}