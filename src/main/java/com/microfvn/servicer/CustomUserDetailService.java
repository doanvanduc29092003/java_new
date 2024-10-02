package com.microfvn.servicer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microfvn.models.CustomUserDetails;
import com.microfvn.models.User;
import com.microfvn.models.UserRole;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserServicer userService; // Sửa tên dịch vụ để đồng bộ với giao diện

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        Set<UserRole> roles = user.getUserRoles();

        for (UserRole userRole : roles) {
            grantedAuthoritySet.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
        }
        return new CustomUserDetails(user, grantedAuthoritySet);
    }
}
