package by.exadel.internship.service;

import by.exadel.internship.entity.user.User;
import by.exadel.internship.entity.user.UserDetailsImpl;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository
                .findByLogin(login)
                .orElseThrow(() -> new NotFoundException("User Not Found with login: " + login, "user.login.invalid"));
        return UserDetailsImpl.build(user);
    }
}
