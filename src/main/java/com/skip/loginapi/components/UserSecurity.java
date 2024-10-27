package com.skip.loginapi.components;

import com.skip.loginapi.entities.User;
import com.skip.loginapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserSecurity {

    @Autowired
    private UserService userService;

    public boolean isOwner(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        // Extract the authenticated user from the SecurityContext
        User user = (User) authentication.getPrincipal();  // Assuming User implements UserDetails

        // Check if the user has the SUPERADMIN role
        boolean isSuperAdmin = user.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_SUPERADMIN"));

        if (isSuperAdmin) {
            //logger.info("User with ID: {} is SUPERADMIN and can access any resource", user.getId());
            return true;  // SUPERADMIN can access any resource
        }

        // If not SUPERADMIN, only allow access if the user ID matches the requested ID
        boolean isOwner = user.getId().equals(id);
        //if (isOwner) {
            //logger.info("User with ID: {} is accessing their own resource", user.getId());
       // } else {
            //logger.warn("User with ID: {} tried to access resource of user with ID: {}", user.getId(), id);
        //}

        return isOwner;
    }
}
