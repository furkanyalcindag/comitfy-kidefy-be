package com.comitfy.kidefy.userModule.controller;

import com.comitfy.kidefy.annotation.ControllerInfo;
import com.comitfy.kidefy.userModule.dto.RoleDTO;
import com.comitfy.kidefy.userModule.dto.UserDTO;
import com.comitfy.kidefy.userModule.dto.requestDTO.UserPasswordRequestDTO;
import com.comitfy.kidefy.userModule.dto.requestDTO.UserRequestDTO;
import com.comitfy.kidefy.userModule.dto.requestDTO.UserRolesRequestDTO;
import com.comitfy.kidefy.userModule.entity.User;
import com.comitfy.kidefy.userModule.mapper.UserMapper;
import com.comitfy.kidefy.userModule.repository.UserRepository;
import com.comitfy.kidefy.userModule.service.RoleService;
import com.comitfy.kidefy.userModule.service.UserService;
import com.comitfy.kidefy.userModule.specification.UserSpecification;
import com.comitfy.kidefy.util.PageDTO;
import com.comitfy.kidefy.util.common.BaseCrudController;
import com.comitfy.kidefy.util.common.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("user-api")
@ControllerInfo(controllerName = "Settings")
public class UserController extends BaseCrudController<UserDTO, UserRequestDTO, User, UserRepository, UserMapper, UserSpecification, UserService> {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleService roleService;

    @Autowired
    HelperService helperService;
   /* @Autowired
    private MinioService minioService;*/

    @Override
    protected UserService getService() {
        return userService;
    }

    @Override
    protected UserMapper getMapper() {
        return userMapper;
    }


    @PostMapping("/create-user")
    public ResponseEntity<UserRequestDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        if(userRequestDTO.getUsername() == null || userRequestDTO.getUsername().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO savedUser = userService.saveUser(userRequestDTO);
        if (savedUser != null) {
            return new ResponseEntity<>(userRequestDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/add-roles/{userId}")
    public ResponseEntity<UserRolesRequestDTO> addRolesToUser(@PathVariable UUID userId, @RequestBody UserRolesRequestDTO userRolesRequestDTO) {
        try {
            userRolesRequestDTO = userService.addRolesToUser(userId, userRolesRequestDTO);
            return new ResponseEntity<>(userRolesRequestDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("get-all-by-role/{roleId}")
    public ResponseEntity<PageDTO<UserDTO>> getByRoleId(
            @PathVariable UUID roleId, @RequestParam int pageNumber, @RequestParam int pageSize) {
        PageDTO<UserDTO> pageDTO = userService.getUserByRole(roleId, pageNumber, pageSize);
        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }

    @GetMapping("get-roles-by-user/{userId}")
    public ResponseEntity<List<RoleDTO>> getRolesByUserId(
            @PathVariable UUID userId) {
        List<RoleDTO> userDTO = userService.getUserRolesByUserId(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


   /* @PostMapping("/user-api")
    public ResponseEntity<UserRequestDTO> saveByUser(@RequestHeader(value = "accept-language", required = true) String acceptLanguage,
                                                     @RequestBody UserRequestDTO userRequestDTO) {
        //UserDTO optional = userService.findByUUID(userId);
        User user = helperService.getUserFromSession();
        if (user != null) {
            {

                return new ResponseEntity<>(userService.saveUserByUser(user.getUuid(), userRequestDTO), HttpStatus.OK);
            }
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }*/
    @PutMapping("/update-password/{id}")
    public ResponseEntity<String> updatePassword(@RequestBody UserPasswordRequestDTO userPasswordRequestDTO, @PathVariable UUID id) {
        try {
            boolean isUpdated = userService.updatePassword(id, userPasswordRequestDTO.getPassword());
            if (isUpdated) {
                return new ResponseEntity<>("Password updated succesfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Password is not updated!",HttpStatus.BAD_REQUEST);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-roles/{userId}")
    public ResponseEntity<UserRolesRequestDTO> updateRoles(@PathVariable UUID userId, @RequestBody UserRolesRequestDTO userRolesRequestDTO) {
        try {
            userRolesRequestDTO = userService.updateRoles(userId, userRolesRequestDTO);
            return new ResponseEntity<>(userRolesRequestDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }






}
