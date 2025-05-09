package com.comitfy.kidefy.app.dto;

import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;


@Data
public class AuthorizationsDTO extends BaseDTO {

    private Long ModuleId;

    private String AuthCode;

    private String AuthName;

    private String AuthDescription;

}
