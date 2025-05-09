package com.comitfy.kidefy.userModule.controller;


import com.comitfy.kidefy.userModule.dto.RoleDTO;
import com.comitfy.kidefy.userModule.dto.UserAbilityDTO;
import com.comitfy.kidefy.userModule.dto.UserDataDTO;
import com.comitfy.kidefy.userModule.dto.UserLoginDTO;
import com.comitfy.kidefy.userModule.entity.Role;
import com.comitfy.kidefy.userModule.entity.User;
import com.comitfy.kidefy.userModule.model.requestModel.auth.LoginRequest;
import com.comitfy.kidefy.userModule.model.requestModel.auth.RegisterRequest;
import com.comitfy.kidefy.userModule.service.UserService;
import com.comitfy.kidefy.userModule.service.interfaces.IAuthService;
import com.comitfy.kidefy.util.common.HelperService;
import com.comitfy.kidefy.util.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthorizationController {

    @Autowired
    IAuthService authService;


    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    HelperService helperService;

    @Autowired
    UserService userService;


    @PostMapping("/register")
    public ResponseEntity<String> registerHandler(@RequestBody RegisterRequest user) {


        authService.registerUser(user);

        return new ResponseEntity<String>("Kullanıcı Eklendi", HttpStatus.OK);

    }


    @PostMapping("/login")
    public ResponseEntity<UserLoginDTO> loginHandler(@RequestBody LoginRequest body) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

        authManager.authenticate(authInputToken);

        String token = jwtUtil.generateToken(body.getEmail());

        List<RoleDTO> roles = new ArrayList<>();
        User user = userService.getUserByEmail(body.getEmail());
        //List<Role> roleList = authService.getRolesByUser(user);
        for (Role role : user.getRoles()) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setName(role.getName());
            roleDTO.setUuid(role.getUuid());
            roles.add(roleDTO);
        }

        UserLoginDTO userLoginDTO = new UserLoginDTO();
        UserDataDTO userDataDTO = new UserDataDTO();
        userDataDTO.setAvatar("/src/assets/images/avatars/avatar-1.png");
        userDataDTO.setRole(roles);
        userDataDTO.setEmail(user.getEmail());
        userDataDTO.setUsername(user.getUsername());
        userDataDTO.setFullName(user.getFirstName() + " " + user.getLastName());
        userDataDTO.setId(user.getUuid());
        userDataDTO.setUserId(user.getId());
        userLoginDTO.setUserData(userDataDTO);

        UserAbilityDTO userAbilityDTO = new UserAbilityDTO();
        userAbilityDTO.setAction("manage");
        userAbilityDTO.setSubject("all");

        userLoginDTO.setUserAbilities(List.of(userAbilityDTO));

        userLoginDTO.setAccessToken(token);


        /*Map<String, Object> authorizationMap = new HashMap<>();
        authorizationMap.put("roles", roles);
        authorizationMap.put("userAbilities", roles);
        authorizationMap.put("jwt-token", token);
        authorizationMap.put("accessToken", token);*/
        //authorizationMap.put("gender", user.getGenderEnum() != null ? user.getGenderEnum().name() : "");
        //Collections.singletonMap("jwt-token", token);

        return new ResponseEntity<>(userLoginDTO, HttpStatus.OK);
    }

    /*@GetMapping("required-contract")
    public ResponseEntity<PageDTO<ContractDTO>> getRequiredContract(@RequestHeader(value = "accept-language", required = true) String language,
                                                                    @RequestParam int pageNumber, @RequestParam int pageSize) {
        PageDTO<ContractDTO> dto = authServiceforContract.getRequiredContract(pageNumber, pageSize, LanguageEnum.valueOf(language));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }*/

}
