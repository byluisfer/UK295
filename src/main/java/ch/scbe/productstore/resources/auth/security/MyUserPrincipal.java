package ch.scbe.productstore.resources.auth.security;

import ch.scbe.productstore.resources.user.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class MyUserPrincipal implements UserDetails {

    private final Users user;

    public MyUserPrincipal(Users user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // THIS is the key: convert "ADMIN" to "ROLE_ADMIN" and so on
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // <- this line makes it all work
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

    public Users getUser() {
        return user;
    }
}
