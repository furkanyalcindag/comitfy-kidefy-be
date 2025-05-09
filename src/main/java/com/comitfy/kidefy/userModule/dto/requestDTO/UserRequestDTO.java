package com.comitfy.kidefy.userModule.dto.requestDTO;

import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;


@Data
public class UserRequestDTO extends BaseDTO {

    private String username;
    private String firstName;
    private String lastName;
}
