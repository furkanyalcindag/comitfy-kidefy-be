package com.comitfy.kidefy.app.dto.request;

import com.comitfy.kidefy.util.common.BaseDTO;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class RolesModulesRequestDTO extends BaseDTO {

    private UUID roleUUID;
    private List<UUID> moduleUUIDs;

}
