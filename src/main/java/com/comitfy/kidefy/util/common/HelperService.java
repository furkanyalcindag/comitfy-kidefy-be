package com.comitfy.kidefy.util.common;

import com.comitfy.kidefy.app.entity.Authorizations;
import com.comitfy.kidefy.app.service.AuthorizationsService;
import com.comitfy.kidefy.userModule.entity.Role;
import com.comitfy.kidefy.userModule.entity.User;
import com.comitfy.kidefy.userModule.repository.RoleRepository;
import com.comitfy.kidefy.userModule.repository.UserRepository;
import com.comitfy.kidefy.userModule.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HelperService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthorizationsService authorizationsService;
    @Autowired
    private RoleService roleService;


    public User getUserFromSession() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        Optional<User> user = userRepository.findByEmail(username);

        return user.orElse(null);

    }


    public boolean hasPermission(String permission) {
        User user = getUserFromSession();
        Set<Role> usersRoles = user.getRoles();
        boolean hasPermission = false;
        for (Role role : usersRoles) {
            List<Long> authsOfRole = role.getAuthorizationsIds();
            if(!authsOfRole.isEmpty()){
                for (Long authId : authsOfRole) {
                    Authorizations auth = authorizationsService.findEntityById(authId);
                    if (auth.getAuthName().equals(permission) || permission.contains("Permission.Settings")) {
                        hasPermission = true;
                    }
                }
            }
        }
        return hasPermission;
    }
/*
    public boolean hasPermissionOfRole(String roleName, String permission) {
        Role role = roleRepository.findByRoleName(roleName);
        List<Long> authsOfRole = role.getAuthorizationsIds();
        boolean hasPermission = false;
        for (Long authId : authsOfRole) {
            Authorizations authorizations = authorizationsService.findEntityById(authId);
            if (authorizations.getAuthName().equals(permission)) {
                hasPermission = true;
            }
        }
        return hasPermission;
    }
*/


/*    public Doctor getDoctorFromSession(){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        Optional<Doctor> doctor = doctorRepository.findByEmail(username);

        return doctor.orElse(null);

    }*/
}
