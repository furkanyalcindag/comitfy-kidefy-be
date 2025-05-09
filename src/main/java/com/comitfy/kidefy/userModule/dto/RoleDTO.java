package com.comitfy.kidefy.userModule.dto;

import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;

@Data
public class RoleDTO extends BaseDTO {
    private String name;
    private Boolean isStaff;
}
