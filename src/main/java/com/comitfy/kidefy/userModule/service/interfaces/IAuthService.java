package com.comitfy.kidefy.userModule.service.interfaces;


import com.comitfy.kidefy.userModule.model.requestModel.auth.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface IAuthService {


    boolean registerUser(RegisterRequest request);

}
