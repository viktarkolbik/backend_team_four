package by.exadel.internship.controller;

import by.exadel.internship.config.jwt.JwtUtils;
import by.exadel.internship.entity.user.UserDetailsImpl;
import by.exadel.internship.pojo.JwtResponse;
import by.exadel.internship.pojo.LoginRequest;
import by.exadel.internship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/sgn")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest){
        System.out.println("Tut");
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()));
        System.out.println("wtf");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        System.out.println("tut2");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> role = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        System.out.println("tut3");
        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                role));
    }
}
