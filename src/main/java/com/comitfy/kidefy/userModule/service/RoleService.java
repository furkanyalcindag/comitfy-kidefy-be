package com.comitfy.kidefy.userModule.service;

import com.comitfy.kidefy.app.dto.request.RolesAuthorizationsRequestDTO;
import com.comitfy.kidefy.app.dto.request.RolesModulesRequestDTO;
import com.comitfy.kidefy.app.entity.Authorizations;
import com.comitfy.kidefy.app.entity.Module;
import com.comitfy.kidefy.app.repository.ModuleRepository;
import com.comitfy.kidefy.app.service.AuthorizationsService;
import com.comitfy.kidefy.app.service.ModuleService;
import com.comitfy.kidefy.userModule.dto.RoleDTO;
import com.comitfy.kidefy.userModule.dto.requestDTO.RoleRequestDTO;
import com.comitfy.kidefy.userModule.entity.Role;
import com.comitfy.kidefy.userModule.mapper.RoleMapper;
import com.comitfy.kidefy.userModule.repository.RoleRepository;
import com.comitfy.kidefy.userModule.specification.RoleSpecification;
import com.comitfy.kidefy.util.common.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoleService extends BaseService<RoleDTO, RoleRequestDTO, Role, RoleRepository, RoleMapper, RoleSpecification> {


    @Autowired
    RoleRepository repository;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleSpecification roleSpecification;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private AuthorizationsService authorizationsService;

    @Override
    public RoleRepository getRepository() {
        return repository;
    }

    @Override
    public RoleMapper getMapper() {
        return roleMapper;
    }

    @Override
    public RoleSpecification getSpecification() {
        return roleSpecification;
    }


    @Transactional
    public boolean saveRolesModules(RolesModulesRequestDTO requestDTO) {
        Role role = findEntityByUUID(requestDTO.getRoleUUID());
        role.getModuleIds().clear();
        getRepository().save(role);

        List<Long> moduleIds = new ArrayList<>();
        for (UUID moduleUUID : requestDTO.getModuleUUIDs()){
            Module module = moduleService.findEntityByUUID(moduleUUID);
            moduleIds.add(module.getId());
        }
        role.setModuleIds(moduleIds);

        getRepository().save(role);


        return true;
    }

    @Transactional
    public boolean saveRolesAuthorizations(RolesAuthorizationsRequestDTO requestDTO) {
        Role role = findEntityByUUID(requestDTO.getRoleUUID());
        role.getAuthorizationsIds().clear();
        getRepository().save(role);

        List<Long> authIds = new ArrayList<>();
        for (UUID authUUID : requestDTO.getAuthUUIDs()){
            Authorizations auth = authorizationsService.findEntityByUUID(authUUID);
            authIds.add(auth.getId());
        }
        role.setAuthorizationsIds(authIds);

        getRepository().save(role);


        return true;
    }

    /*
    public boolean updateRolesModules(RolesModulesRequestDTO requestDTO) {

        Role role = findEntityByUUID(requestDTO.getRoleUUID());
        if (role == null) {
            return false;
        }

        List<Long> existingModuleIds = role.getModuleIds();
        List<Long> moduleIdsToAdd = new ArrayList<>();

        for (UUID moduleUUID : requestDTO.getModuleUUIDs()) {
            Module module = moduleService.findEntityByUUID(moduleUUID);
            Long moduleId = module.getId();
            if (!existingModuleIds.contains(moduleId)) {
                moduleIdsToAdd.add(moduleId);
            }
        }

        if (!moduleIdsToAdd.isEmpty()) {
            existingModuleIds.addAll(moduleIdsToAdd);
            role.setModuleIds(existingModuleIds);
            getRepository().save(role);
        }

        return true;
    }
     */








}
