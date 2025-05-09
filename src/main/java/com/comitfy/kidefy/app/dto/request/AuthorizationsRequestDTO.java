package com.comitfy.kidefy.app.dto.request;
import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;

@Data
public class AuthorizationsRequestDTO extends BaseDTO {

    private Long ModuleId;

    private String AuthCode;

    private String AuthName;

    private String AuthDescription;

}
