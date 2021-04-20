package by.exadel.internship.controller;

import by.exadel.internship.config.jwt.JwtService;
import by.exadel.internship.entity.UserDetailsImpl;
import by.exadel.internship.pojo.JwtResponse;
import by.exadel.internship.pojo.LoginRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = "Authorization controller")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private static final String EMAIL_SEPARATOR = "@";

    @PostMapping("/signIn")
    @ApiOperation("Authorize method")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
        splitEmail(loginRequest);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> role = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                role));
    }

    private void splitEmail(LoginRequest loginRequest) {
        if (StringUtils.contains(loginRequest.getLogin(), EMAIL_SEPARATOR)) {
            loginRequest.setLogin(StringUtils.substringBefore(loginRequest.getLogin(), EMAIL_SEPARATOR));
        }
    }
}
