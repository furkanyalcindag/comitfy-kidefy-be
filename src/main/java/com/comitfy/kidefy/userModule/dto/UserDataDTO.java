package com.comitfy.kidefy.userModule.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserDataDTO {

    private String avatar;
    private String email;
    private String fullName;
    private UUID id;
    private List<RoleDTO> role;
    private String username;
    private Long userId;

}
