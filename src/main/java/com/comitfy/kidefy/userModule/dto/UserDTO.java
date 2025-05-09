package com.comitfy.kidefy.userModule.dto;


import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO extends BaseDTO {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String photoLink;
    private List<RoleDTO> roles;

}