package com.comitfy.kidefy.app.dto.request;

import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;


@Data
public class ModuleRequestDTO extends BaseDTO {
    private long ustId;
    private String code;
    private String name;
    private String description;
    private Long manualId;
}
