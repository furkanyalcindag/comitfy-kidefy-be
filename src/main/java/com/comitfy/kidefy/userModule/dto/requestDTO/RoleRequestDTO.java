package com.comitfy.kidefy.userModule.dto.requestDTO;

import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class RoleRequestDTO extends BaseDTO {
    private String name;
    private Boolean isStaff;
    private List<UUID> moduleUUIDs;
    private List<UUID> authUUIDs;
}
