package com.comitfy.kidefy.userModule.dto.requestDTO;

import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;

@Data
public class UserPasswordRequestDTO extends BaseDTO {
    private String password;
}
