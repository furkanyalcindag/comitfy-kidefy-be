package com.comitfy.kidefy.userModule.dto.requestDTO;

import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserRolesRequestDTO extends BaseDTO {

    private List<UUID> roleIds;
}

