package by.exadel.internship.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class UserDetailsImpl implements UserDetails {

    private UUID id;
    private String lastName;
    private String firstName;
    private String email;
    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(UUID id, String lastName, String firstName, String email,
                           String login, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getUserRole().name()));

        return new UserDetailsImpl(
                user.getId(),
                user.getLastName(),
                user.getFirstName(),
                user.getEmail(),
                user.getLogin(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    public UUID getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
