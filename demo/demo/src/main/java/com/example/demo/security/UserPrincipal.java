package com.example.demo.security;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.constant.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private Integer id;
    @JsonIgnore
    private String email;
    private String fullName;

    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(User user) {
        Set<Role> rol = user.getRoles();
        List<Role> roles = new ArrayList<>(rol);
        List<String> rights = getPermission(roles);
        List<GrantedAuthority> authorities = rights.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
        return new UserPrincipal(user.getId(), user.getEmail(), user.getFullName(), user.getPassword(), authorities);
    }

    private static List<String> getPermission(Collection<Role> roles) {
        List<String> rights = new ArrayList<>();
        for (Role role : roles) {
            rights.add(Constant.ROLE_PREFIX.concat(Constant.ROLE_PERMISSION_CONNECTOR).concat(role.getName()));
        }
        return rights;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
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

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
