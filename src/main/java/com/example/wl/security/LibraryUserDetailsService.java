package com.example.wl.security;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.wl.datamodel.dao.Library;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.example.wl.datamodel.domainmodel.User user = Library.users.get(username);
        String pwd = user.getPassword();

        if (pwd == null) {
            throw new UsernameNotFoundException(String.format("User with the username %s doesn't exist", username));
        }

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getRole());

        return new User(username, pwd, authorities);
    }
}

