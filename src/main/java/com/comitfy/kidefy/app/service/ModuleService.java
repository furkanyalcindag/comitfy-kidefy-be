package com.comitfy.kidefy.app.service;

import com.comitfy.kidefy.app.dto.ModuleDTO;
import com.comitfy.kidefy.app.dto.request.ModuleRequestDTO;
import com.comitfy.kidefy.app.entity.Module;
import com.comitfy.kidefy.app.mapper.ModuleMapper;
import com.comitfy.kidefy.app.repository.ModuleRepository;
import com.comitfy.kidefy.app.specification.ModuleSpecification;
import com.comitfy.kidefy.userModule.entity.Role;
import com.comitfy.kidefy.userModule.entity.User;
import com.comitfy.kidefy.userModule.service.RoleService;
import com.comitfy.kidefy.util.common.BaseService;
import com.comitfy.kidefy.util.common.HelperService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ModuleService extends BaseService<ModuleDTO, ModuleRequestDTO, Module, ModuleRepository, ModuleMapper, ModuleSpecification> {

    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    ModuleMapper moduleMapper;
    @Autowired
    ModuleSpecification moduleSpecification;
    @Autowired
    private HelperService helperService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ModuleRepository getRepository() {
        return moduleRepository;
    }

    @Override
    public ModuleMapper getMapper() {
        return moduleMapper;
    }

    @Override
    public ModuleSpecification getSpecification() {
        return moduleSpecification;
    }

    public String serializeIcon(ModuleDTO moduleDTO) {
        try {
            Object iconObject = new Object() {
                public String icon = moduleDTO.getIcon();
            };
            return objectMapper.writeValueAsString(iconObject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<ModuleDTO> getUserRolesModules(UUID roleUUID) {
        User user = helperService.getUserFromSession();

        Set<Role> roles = user.getRoles();

        Role requestedRole = roleService.findEntityByUUID(roleUUID);

        if (!roles.contains(requestedRole)) {
            return new ArrayList<>();
        }

        List<ModuleDTO> moduleList = new ArrayList<>();
        List<Module> moduleEntityList = new ArrayList<>();

        for (Long moduleId : requestedRole.getModuleIds()) {
            Module module = moduleRepository.findById(moduleId).orElse(null);
            if (module != null) {
                moduleEntityList.add(module);
            }
        }

        for (Module module : moduleEntityList) {
            ModuleDTO childModule = getMapper().entityToDTO(module);

            String iconJson = serializeIcon(childModule);
            System.out.println("Serialized Icon JSON: " + iconJson);

            if (module.getTopId() != 0) {
                Module moduleTop = moduleRepository.findById(module.getTopId()).orElse(null);
                if (moduleTop == null) continue;

                ModuleDTO topModuleDTO = null;

                for (ModuleDTO m : moduleList) {
                    if (m.getId().equals(moduleTop.getId())) {
                        topModuleDTO = m;
                        break;
                    }
                }

                if (topModuleDTO == null) {
                    topModuleDTO = getMapper().entityToDTO(moduleTop);
                    topModuleDTO.setChildren(new ArrayList<>());
                    topModuleDTO.setIcon(serializeIcon(topModuleDTO));
                    moduleList.add(topModuleDTO);
                }

                if (topModuleDTO.getChildren() == null) {
                    topModuleDTO.setChildren(new ArrayList<>());
                }

                childModule.setIcon(serializeIcon(childModule));
                topModuleDTO.getChildren().add(childModule);

            } else {
                childModule.setIcon(serializeIcon(childModule));
                if (childModule.getChildren() == null || !childModule.getChildren().isEmpty()) {
                    moduleList.add(childModule);
                }
            }
        }

        moduleList.removeIf(moduleDTO -> moduleDTO.getChildren() != null && moduleDTO.getChildren().isEmpty());

        Set<Long> ids = new HashSet<>();
        moduleList.removeIf(moduleDTO -> !ids.add(moduleDTO.getId()));

        moduleList.sort(Comparator.comparing(ModuleDTO::getManualId));

        return moduleList;
    }



    public List<ModuleDTO> getModuleByRole(UUID roleUUID) {
        List<ModuleDTO> moduleDTOs = new ArrayList<>();
        Role role = roleService.findEntityByUUID(roleUUID);

        if (role == null) {
            return null;
        }

        List<Module> moduleEntityList = new ArrayList<>();

        for (Long moduleId : role.getModuleIds()) {
            Module module = moduleRepository.findById(moduleId).orElse(null);
            if (module != null) {
                moduleEntityList.add(module);
            }
        }

        for (Module module : moduleEntityList) {
            ModuleDTO childModule = getMapper().entityToDTO(module);

            if (module.getTopId() != 0) {
                Module moduleTop = moduleRepository.findById(module.getTopId()).orElse(null);
                if (moduleTop == null) continue;

                ModuleDTO topModuleDTO = null;

                for (ModuleDTO m : moduleDTOs) {
                    if (m.getId().equals(moduleTop.getId())) {
                        topModuleDTO = m;
                        break;
                    }
                }

                if (topModuleDTO == null) {
                    topModuleDTO = getMapper().entityToDTO(moduleTop);
                    topModuleDTO.setChildren(new ArrayList<>());
                    moduleDTOs.add(topModuleDTO);
                }

                if (topModuleDTO.getChildren() == null) {
                    topModuleDTO.setChildren(new ArrayList<>());
                }

                topModuleDTO.getChildren().add(childModule);
            } else {
                moduleDTOs.add(childModule);
            }
        }

        moduleDTOs.removeIf(moduleDTO -> moduleDTO.getChildren() != null && moduleDTO.getChildren().isEmpty());

        Set<Long> ids = new HashSet<>();
        moduleDTOs.removeIf(moduleDTO -> !ids.add(moduleDTO.getId()));
        moduleDTOs.sort(Comparator.comparing(ModuleDTO::getManualId));

        return moduleDTOs;
    }

    public List<ModuleDTO> getModule() {
        List<ModuleDTO> moduleDTOs = new ArrayList<>();
        List<Module> moduleEntityList = moduleRepository.findAll();

        for (Module module : moduleEntityList) {
            ModuleDTO childModule = getMapper().entityToDTO(module);

            if (module.getTopId() != 0) {
                Module moduleTop = moduleRepository.findById(module.getTopId()).orElse(null);
                if (moduleTop == null) continue;

                ModuleDTO topModuleDTO = null;

                for (ModuleDTO m : moduleDTOs) {
                    if (m.getId().equals(moduleTop.getId())) {
                        topModuleDTO = m;
                        break;
                    }
                }

                if (topModuleDTO == null) {
                    topModuleDTO = getMapper().entityToDTO(moduleTop);
                    topModuleDTO.setChildren(new ArrayList<>());
                    moduleDTOs.add(topModuleDTO);
                }

                if (topModuleDTO.getChildren() == null) {
                    topModuleDTO.setChildren(new ArrayList<>());
                }

                topModuleDTO.getChildren().add(childModule);
            } else {
                moduleDTOs.add(childModule);
            }
        }

        moduleDTOs.removeIf(moduleDTO -> moduleDTO.getChildren() != null && moduleDTO.getChildren().isEmpty());

        Set<Long> ids = new HashSet<>();
        moduleDTOs.removeIf(moduleDTO -> !ids.add(moduleDTO.getId()));
        moduleDTOs.sort(Comparator.comparing(ModuleDTO::getManualId));

        return moduleDTOs;
    }


}
