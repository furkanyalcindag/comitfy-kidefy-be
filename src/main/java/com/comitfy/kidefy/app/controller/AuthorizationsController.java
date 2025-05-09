package com.comitfy.kidefy.app.controller;

import com.comitfy.kidefy.annotation.ControllerInfo;
import com.comitfy.kidefy.app.dto.AuthorizationsDTO;
import com.comitfy.kidefy.app.dto.ModuleDTO;
import com.comitfy.kidefy.app.dto.request.AuthorizationsRequestDTO;
import com.comitfy.kidefy.app.entity.Authorizations;
import com.comitfy.kidefy.app.mapper.AuthorizationsMapper;
import com.comitfy.kidefy.app.repository.AuthorizationsRepository;
import com.comitfy.kidefy.app.service.AuthorizationsService;
import com.comitfy.kidefy.app.specification.AuthorizationsSpecification;
import com.comitfy.kidefy.util.common.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("authorizations")
@ControllerInfo(controllerName = "AuthController")
public class AuthorizationsController extends BaseCrudController<AuthorizationsDTO, AuthorizationsRequestDTO, Authorizations, AuthorizationsRepository, AuthorizationsMapper, AuthorizationsSpecification, AuthorizationsService> {
    @Autowired
    AuthorizationsMapper authorizationsMapper;

    @Autowired
    AuthorizationsService authService;

    @Override
    public AuthorizationsService getService() {
        return authService;
    }

    @Override
    public AuthorizationsMapper getMapper() {
        return authorizationsMapper;
    }


    @GetMapping("get-auth-by-roles/{roleUUID}")
    public ResponseEntity<List<ModuleDTO>> getRolesAuthorizations(@PathVariable UUID roleUUID) {
        List<ModuleDTO> authorizationsDTOS = getService().getRolesAuthorizations(roleUUID);
        return new ResponseEntity<>(authorizationsDTOS, HttpStatus.OK);
    }

    @GetMapping("get-auth")
    public ResponseEntity<List<ModuleDTO>> getAuthorizations() {
        List<ModuleDTO> modules = getService().getAuthorization();
        return new ResponseEntity<>(modules, HttpStatus.OK);
    }

}
