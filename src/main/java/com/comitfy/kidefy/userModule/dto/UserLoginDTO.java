package com.comitfy.kidefy.userModule.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserLoginDTO {

    private String accessToken;
    private UserDataDTO userData;
    private List<UserAbilityDTO> userAbilities;


}
