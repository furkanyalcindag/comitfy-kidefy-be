package com.comitfy.kidefy.userModule.service;

import com.comitfy.kidefy.userModule.dto.RoleDTO;
import com.comitfy.kidefy.userModule.dto.UserDTO;
import com.comitfy.kidefy.userModule.dto.requestDTO.UserRequestDTO;
import com.comitfy.kidefy.userModule.dto.requestDTO.UserRolesRequestDTO;
import com.comitfy.kidefy.userModule.entity.Role;
import com.comitfy.kidefy.userModule.entity.User;
import com.comitfy.kidefy.userModule.mapper.RoleMapper;
import com.comitfy.kidefy.userModule.mapper.UserMapper;
import com.comitfy.kidefy.userModule.repository.RoleRepository;
import com.comitfy.kidefy.userModule.repository.UserRepository;
import com.comitfy.kidefy.userModule.specification.UserSpecification;
import com.comitfy.kidefy.util.PageDTO;
import com.comitfy.kidefy.util.common.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseService<UserDTO, UserRequestDTO, User, UserRepository, UserMapper, UserSpecification> {

    @Autowired
    UserSpecification userSpecification;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public UserRepository getRepository() {
        return userRepository;
    }

    @Override
    public UserMapper getMapper() {
        return userMapper;
    }

    @Override
    public UserSpecification getSpecification() {
        return userSpecification;
    }


    public User getUserByEmail(String email) {
        return getRepository().findByEmail(email).orElse(null);
    }

    public UserDTO saveUser(UserRequestDTO userRequestDTO) {

        String email = userRequestDTO.getUsername();
        String username = userRequestDTO.getUsername();

        User existingUser = userRepository.findByEmail(email).orElse(null);
        if (existingUser != null) {
            throw new RuntimeException("This user already exists");
        }

        String randomPassword = generateRandomPassword();

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setFirstName(userRequestDTO.getFirstName());
        newUser.setLastName(userRequestDTO.getLastName());
        newUser.setPassword(passwordEncoder.encode(randomPassword));
        User savedUser = userRepository.save(newUser);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(savedUser.getEmail());
        userDTO.setFirstName(savedUser.getFirstName());
        userDTO.setLastName(savedUser.getLastName());
        return userDTO;
    }


    private String generateRandomPassword() {
        int length = 10;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    @Transactional
    public UserRolesRequestDTO addRolesToUser(UUID userId, UserRolesRequestDTO userRolesRequestDTO) {

        Optional<User> optionalUser = userRepository.findByUuid(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<UUID> roleIds = userRolesRequestDTO.getRoleIds();

            List<Role> rolesToAdd = roleIds.stream()
                    .map(roleId -> roleRepository.findByUuid(roleId)
                            .orElseThrow(() -> new NoSuchElementException("Role not found with id: " + roleId)))
                    .collect(Collectors.toList());

            user.getRoles().addAll(rolesToAdd);

            userRepository.save(user);
            return userRolesRequestDTO;
        } else {
            throw new NoSuchElementException("User not found with id: " + userId);
        }
    }

    public PageDTO<UserDTO> getUserByRole(UUID roleId, int page, int size) {
        Optional<Role> role = roleRepository.findByUuid(roleId);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("id")));
        if (role.isPresent()) {
            Page<User> pageEntity = userRepository.getUserByRole(pageable, roleId);
            PageDTO<UserDTO> pageDTO = new PageDTO<>();
            pageDTO.setNumber(pageEntity.getNumber());
            pageDTO.setSize(pageEntity.getSize());
            pageDTO.setTotalPage(pageEntity.getTotalPages());
            pageDTO.setSort(pageEntity.getSort());
            pageDTO.setData(pageEntity.toList().stream().map(userMapper::entityToDTO).collect(Collectors.toList()));
            return pageDTO;
        } else {
            return null;
        }
    }

    public List<RoleDTO> getUserRolesByUserId(UUID userId) {
        Optional<User> userOptional = userRepository.findByUuid(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Set<Role> roles = user.getRoles();

            List<RoleDTO> roleDTOs = roles.stream().map(roleMapper::entityToDTO).collect(Collectors.toList());

            return roleDTOs;
        } else {
            return null;
        }
    }


    public UserRequestDTO saveUserByUser(UUID id, UserRequestDTO dto) {
        Optional<User> user = userRepository.findByUuid(id);
        if (user.isPresent()) {
            User user1 = getMapper().requestDTOToEntity(dto);
            userRepository.save(user1);
            return dto;
        } else {
            return null;
        }
    }
    public boolean updatePassword(UUID userId, String newPassword) {
        Optional<User> optionalUser = userRepository.findByUuid(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
    public UserRolesRequestDTO updateRoles(UUID userId, UserRolesRequestDTO userRolesRequestDTO) {
        Optional<User> optionalUser = userRepository.findByUuid(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<UUID> roleIds = userRolesRequestDTO.getRoleIds();

            user.getRoles().clear();

            List<Role> newRoles = roleIds.stream()
                    .map(roleId -> roleRepository.findByUuid(roleId)
                            .orElseThrow(() -> new NoSuchElementException("Role not found with id: " + roleId)))
                    .collect(Collectors.toList());

            user.getRoles().addAll(newRoles);

            userRepository.save(user);
            return userRolesRequestDTO;
        } else {
            throw new NoSuchElementException("User not found with id: " + userId);
        }
    }

    @Override
    public UserDTO update(UUID userId, UserRequestDTO dto) {
        Optional<User> optionalUser = getRepository().findByUuid(userId);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            User updatedUser = getMapper().requestDTOToEntity(dto);
            existingUser.setEmail(dto.getUsername());
            getMapper().update(existingUser, updatedUser);
            getRepository().save(existingUser);
            return getMapper().entityToDTO(existingUser);
        } else {
            return null;
        }
    }

}

