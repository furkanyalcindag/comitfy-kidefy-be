package com.comitfy.kidefy.app.service;

import com.comitfy.kidefy.app.dto.AuthorizationsDTO;
import com.comitfy.kidefy.app.dto.ModuleDTO;
import com.comitfy.kidefy.app.dto.request.AuthorizationsRequestDTO;
import com.comitfy.kidefy.app.entity.Authorizations;
import com.comitfy.kidefy.app.entity.Module;
import com.comitfy.kidefy.app.mapper.AuthorizationsMapper;
import com.comitfy.kidefy.app.repository.AuthorizationsRepository;
import com.comitfy.kidefy.app.repository.ModuleRepository;
import com.comitfy.kidefy.app.specification.AuthorizationsSpecification;
import com.comitfy.kidefy.userModule.entity.Role;
import com.comitfy.kidefy.userModule.service.RoleService;
import com.comitfy.kidefy.util.common.BaseService;
import com.comitfy.kidefy.util.common.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorizationsService extends BaseService <AuthorizationsDTO, AuthorizationsRequestDTO, Authorizations, AuthorizationsRepository, AuthorizationsMapper, AuthorizationsSpecification>{
    @Autowired
    AuthorizationsRepository authRepository;

    @Autowired
    AuthorizationsMapper authorizationsMapper;

    @Autowired
    AuthorizationsSpecification authSpecification;
    @Autowired
    private HelperService helperService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public AuthorizationsRepository getRepository() {
        return authRepository;
    }

    @Override
    public AuthorizationsMapper getMapper() {
        return authorizationsMapper;
    }

    @Override
    public AuthorizationsSpecification getSpecification() {
        return authSpecification;
    }

    public List<ModuleDTO> getRolesAuthorizations(UUID roleUUID) {

        Role requestedRole = roleService.findEntityByUUID(roleUUID);

        // Authorizations'ları al
        List<Authorizations> authorizationsEntityList = new ArrayList<>();
        for (Long authId : requestedRole.getAuthorizationsIds()) {
            Authorizations authorizations = authRepository.findById(authId).orElse(null);
            if (authorizations != null) {
                authorizationsEntityList.add(authorizations);
            }
        }

        // Module'leri ve AuthorizationsDTO'ları eşle
        Map<Long, ModuleDTO> moduleMap = new HashMap<>();
        for (Authorizations auth : authorizationsEntityList) {
            Long moduleId = auth.getModuleId();

            // Modül zaten eklendiyse devam et, değilse ekle
            ModuleDTO moduleDTO = moduleMap.get(moduleId);
            if (moduleDTO == null) {
                Module module = moduleRepository.findById(moduleId).orElse(null);
                if (module != null) {
                    moduleDTO = new ModuleDTO();
                    moduleDTO.setId(module.getId());
                    moduleDTO.setTitle(module.getTitle());
                    moduleDTO.setAuthorizations(new ArrayList<>());
                    moduleMap.put(moduleId, moduleDTO);
                }
            }

            // Authorization'ı DTO'ya çevir ve listeye ekle
            if (moduleDTO != null) {
                AuthorizationsDTO authDTO = new AuthorizationsDTO();
                authDTO.setId(auth.getId());
                authDTO.setUuid(auth.getUuid());
                authDTO.setAuthName(auth.getAuthName());
                authDTO.setAuthCode(auth.getAuthCode());
                authDTO.setAuthDescription(auth.getAuthDescription());
                moduleDTO.getAuthorizations().add(authDTO);
            }
        }

        // Modül DTO'larını liste olarak döndür
        return new ArrayList<>(moduleMap.values());
    }

    public List<ModuleDTO> getAuthorization() {
        // Tüm Authorizations'ları al
        List<Authorizations> authorizationsEntityList = authRepository.findAll();

        // Module ve AuthorizationsDTO'ları eşle
        Map<Long, ModuleDTO> moduleMap = new HashMap<>();
        for (Authorizations auth : authorizationsEntityList) {
            Long moduleId = auth.getModuleId();

            // Modül zaten eklendiyse devam et, değilse ekle
            ModuleDTO moduleDTO = moduleMap.get(moduleId);
            if (moduleDTO == null) {
                Module module = moduleRepository.findById(moduleId).orElse(null);
                if (module != null) {
                    moduleDTO = new ModuleDTO();
                    moduleDTO.setId(module.getId());
                    moduleDTO.setTitle(module.getTitle());
                    moduleDTO.setAuthorizations(new ArrayList<>());
                    moduleMap.put(moduleId, moduleDTO);
                }
            }

            // Authorization'ı DTO'ya çevir ve listeye ekle
            if (moduleDTO != null) {
                AuthorizationsDTO authDTO = new AuthorizationsDTO();
                authDTO.setId(auth.getId());
                authDTO.setUuid(auth.getUuid());
                authDTO.setAuthName(auth.getAuthName());
                authDTO.setAuthCode(auth.getAuthCode());
                authDTO.setAuthDescription(auth.getAuthDescription());
                moduleDTO.getAuthorizations().add(authDTO);
            }
        }

        // Modül DTO'larını liste olarak döndür
        return new ArrayList<>(moduleMap.values());
    }







}
