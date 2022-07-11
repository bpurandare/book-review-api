package com.tcs.bookreview.security;

import com.tcs.bookreview.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailImpl implements UserDetails {

    private User user;

    public UserDetailImpl(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return authorities;
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
        return calculateifEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return calculateifEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return calculateifEnabled();
    }

    @Override
    public boolean isEnabled() {
        return calculateifEnabled();
    }

    private boolean calculateifEnabled(){
        return "Y".equals(user.getEnabled());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
