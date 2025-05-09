package com.comitfy.kidefy.app.controller;

import com.comitfy.kidefy.annotation.ControllerInfo;
import com.comitfy.kidefy.app.dto.ModuleDTO;
import com.comitfy.kidefy.app.dto.request.ModuleRequestDTO;
import com.comitfy.kidefy.app.entity.Module;
import com.comitfy.kidefy.app.mapper.ModuleMapper;
import com.comitfy.kidefy.app.repository.ModuleRepository;
import com.comitfy.kidefy.app.service.ModuleService;
import com.comitfy.kidefy.app.specification.ModuleSpecification;
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
@RequestMapping("module")
@ControllerInfo(controllerName = "Settings")
public class ModuleController extends BaseCrudController<ModuleDTO, ModuleRequestDTO, Module, ModuleRepository, ModuleMapper, ModuleSpecification, ModuleService> {

    @Autowired
    ModuleMapper moduleMapper;

    @Autowired
    ModuleService moduleService;

    @Override
    public ModuleService getService() {
        return moduleService;
    }

    @Override
    public ModuleMapper getMapper() {
        return moduleMapper;
    }

    @GetMapping("get-module-by-user/{roleUUID}")
    public ResponseEntity<List<ModuleDTO>> getModuleByUser(@PathVariable UUID roleUUID) {
        List<ModuleDTO> modules = getService().getUserRolesModules(roleUUID);
        return new ResponseEntity<>(modules, HttpStatus.OK);
    }

    @GetMapping("get-module-by-role/{roleUUID}")
    public ResponseEntity<List<ModuleDTO>> getModuleByRole(@PathVariable UUID roleUUID) {
        List<ModuleDTO> modules = getService().getModuleByRole(roleUUID);
        return new ResponseEntity<>(modules, HttpStatus.OK);
    }

    @GetMapping("get-module")
    public ResponseEntity<List<ModuleDTO>> getModule() {
        List<ModuleDTO> modules = getService().getModule();
        return new ResponseEntity<>(modules, HttpStatus.OK);
    }


}
