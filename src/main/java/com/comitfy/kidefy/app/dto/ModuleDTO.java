package com.comitfy.kidefy.app.dto;

import com.comitfy.kidefy.util.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ModuleDTO extends BaseDTO {

    @JsonProperty("id")
    private Long id;
    private Long topId;
    private String title;
    private String icon;
    private String targetPath;
    private List<ModuleDTO> children;
    private List<AuthorizationsDTO> authorizations;
    private Long manualId;


}
