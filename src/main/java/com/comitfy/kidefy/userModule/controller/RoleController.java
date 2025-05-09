package com.comitfy.kidefy.userModule.controller;

import com.comitfy.kidefy.annotation.ControllerInfo;
import com.comitfy.kidefy.app.dto.request.RolesAuthorizationsRequestDTO;
import com.comitfy.kidefy.app.dto.request.RolesModulesRequestDTO;
import com.comitfy.kidefy.userModule.dto.RoleDTO;
import com.comitfy.kidefy.userModule.dto.requestDTO.RoleRequestDTO;
import com.comitfy.kidefy.userModule.entity.Role;
import com.comitfy.kidefy.userModule.mapper.RoleMapper;
import com.comitfy.kidefy.userModule.repository.RoleRepository;
import com.comitfy.kidefy.userModule.service.RoleService;
import com.comitfy.kidefy.userModule.specification.RoleSpecification;
import com.comitfy.kidefy.util.common.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("user-api/role")
@ControllerInfo(controllerName = "Settings")
public class RoleController extends BaseCrudController<RoleDTO, RoleRequestDTO, Role, RoleRepository, RoleMapper, RoleSpecification, RoleService> {

    @Autowired
    RoleService roleService;

    @Autowired
    RoleMapper roleMapper;


    @Override
    protected RoleService getService() {
        return roleService;
    }

    @Override
    protected RoleMapper getMapper() {
        return roleMapper;
    }

    @PostMapping("save-modules-to-role")
    public ResponseEntity<Boolean> saveModulesToRole(@RequestBody RolesModulesRequestDTO requestDTO){
        return new ResponseEntity<>(getService().saveRolesModules(requestDTO), HttpStatus.OK);
    }

    @PostMapping("save-authorizations-to-role")
    public ResponseEntity<Boolean> saveAuthorizationsToRole(@RequestBody RolesAuthorizationsRequestDTO requestDTO){
        return new ResponseEntity<>(getService().saveRolesAuthorizations(requestDTO), HttpStatus.OK);
    }

    /*
    @PutMapping("update-modules-to-role")
    public ResponseEntity<Boolean> updateRolesModules(@RequestBody RolesModulesRequestDTO requestDTO){
        return new ResponseEntity<>(getService().updateRolesModules(requestDTO), HttpStatus.OK);
    }
     */

}
