package rub.demo.backend.web.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rub.demo.backend.domain.model.User;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private Long id;

    private String email;

    private String password;

    private String name;

    private String role;

    private Collection<? extends GrantedAuthority> grantedAuthorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static CustomUserDetails fromUserToCustomUserDetails(User user) {
        CustomUserDetails customUserDetails = new CustomUserDetails();

        customUserDetails.id = user.getId();
        customUserDetails.email = user.getEmail();
        customUserDetails.password = user.getPassword();
        customUserDetails.name = user.getName();
        customUserDetails.role = user.getRole();
        customUserDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));

        return customUserDetails;
    }
}
