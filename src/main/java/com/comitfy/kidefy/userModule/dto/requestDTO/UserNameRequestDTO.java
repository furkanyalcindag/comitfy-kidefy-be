package com.comitfy.kidefy.userModule.dto.requestDTO;

import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;

@Data
public class UserNameRequestDTO extends BaseDTO {

    private String firstName;
    private String lastName;

}
