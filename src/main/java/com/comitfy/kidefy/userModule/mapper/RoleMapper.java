package com.comitfy.kidefy.userModule.mapper;

import com.comitfy.kidefy.userModule.dto.RoleDTO;
import com.comitfy.kidefy.userModule.dto.requestDTO.RoleRequestDTO;
import com.comitfy.kidefy.userModule.entity.Role;
import com.comitfy.kidefy.util.common.AutoCompleteDTO;
import com.comitfy.kidefy.util.common.BaseMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<RoleDTO, RoleRequestDTO, Role> {
    /*@Override
    public RoleDTO entityToDTO(Role entity) {
        RoleDTO roleDTO = new RoleDTO();

        BeanUtils.copyProperties(entity, roleDTO);
        return roleDTO;
    }

    @Override
    public Role dtoToEntity(RoleDTO dto) {

        Role role = new Role();
        role.setName(dto.getName());
        return role;
    }

    @Override
    public Role requestDTOToEntity(RoleRequestDTO dto) {
        Role role = new Role();
        role.setName(dto.getName());
        return role;
    }

    @Override
    public Role requestDTOToExistEntity(Role entity, RoleRequestDTO dto) {

        entity.setName(dto.getName());
        return entity;
    }

    @Override
    public List<Role> dtoListToEntityList(List<RoleDTO> roleDTOS) {
        List<Role> roleList = new ArrayList<>();

        for (RoleDTO roleDTO : roleDTOS) {
            Role role = new Role();

            BeanUtils.copyProperties(roleDTO, role);

            roleList.add(role);

        }

        return roleList;
    }

    @Override
    public List<RoleDTO> entityListToDTOList(List<Role> roles) {

        List<RoleDTO> roleDTOList = new ArrayList<>();

        for (Role role : roles) {
            RoleDTO roleDTO = new RoleDTO();

            BeanUtils.copyProperties(role, roleDTO);

            roleDTOList.add(roleDTO);

        }

        return roleDTOList;
    }

    @Override
    public PageDTO<RoleDTO> pageEntityToPageDTO(Page<Role> pageEntity) {
       /* Page<RoleDTO> dtoPage = pageEntity.map(t -> {
            RoleDTO dto = new RoleDTO();
            dto = entityToDTO(t);
            // Conversion logic
            return dto;
            //return new ModelMapper().map(t, BookDto.class);
        });

        return dtoPage;//

        PageDTO<RoleDTO> pageDTO = new PageDTO<RoleDTO>();
        List<Role> entityList = pageEntity.toList();
        List<RoleDTO> languageDTOList = entityListToDTOList(entityList);
        pageDTO.setStart(pageEntity, languageDTOList);

        return pageDTO;
    }*/

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "uuid", ignore = true),
            @Mapping(target = "deleted", ignore = true),
            @Mapping(target = "creationDate", ignore = true),
            @Mapping(target = "updatedDate", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "lastModifiedBy", ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Role entity, Role updateEntity);


    @Mappings({@Mapping(source = "uuid", target = "value"),
            @Mapping(source = "name", target = "label")

    })
    AutoCompleteDTO entityToAutoCompleteDTO(Role entity);

    RoleDTO entityToDTO(Role role);
    Role dtoToEntity(RoleDTO roleDTO);

}
