package com.comitfy.kidefy.userModule.mapper;

import com.comitfy.kidefy.userModule.dto.UserDTO;
import com.comitfy.kidefy.userModule.dto.requestDTO.UserRequestDTO;
import com.comitfy.kidefy.userModule.entity.User;
import com.comitfy.kidefy.util.common.AutoCompleteDTO;
import com.comitfy.kidefy.util.common.BaseMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserDTO, UserRequestDTO, User> {

    /*@Override
    public UserDTO entityToDTO(User entity) {

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(entity.getEmail());
        userDTO.setFirstName(entity.getFirstName());
        userDTO.setLastName(entity.getLastName());
        userDTO.setEmail(entity.getEmail());
        userDTO.setPhotoLink(entity.getPhotoLink());
        userDTO.setUuid(entity.getUuid());

        userDTO.setGenderEnum(entity.getGenderEnum());
        userDTO.setAgeRangeEnum(entity.getAgeRangeEnum());


     /*   Set<RoleDTO> roleDTOS = new HashSet<>();
        for (Role role : entity.getRoles()) {

            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setName(role.getName());
            roleDTO.setUuid(role.getUuid());
            roleDTOS.add(roleDTO);
        }


        return userDTO;
    }

    @Override
    public User dtoToEntity(UserDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhotoLink(dto.getPhotoLink());
        return user;

    }

    @Override
    public User requestDTOToEntity(UserRequestDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setAgeRangeEnum(dto.getAgeRangeEnum());
        user.setGenderEnum(dto.getGenderEnum());
        user.setUsername(dto.getEmail());
        //user.setPhotoLink(dto.getPhotoLink());
        user.setRoles(new HashSet<>());
    //    user.setChatRoomSet(new HashSet<>());

        return user;
    }


    @Override
    public User requestDTOToExistEntity(User user, UserRequestDTO dto) {

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setGenderEnum(dto.getGenderEnum());
        user.setAgeRangeEnum(dto.getAgeRangeEnum());
        //user.setPhotoLink(dto.getPhotoLink());
        return user;
    }

    public User requestDTOToExistEntityforGender(User user, UserGenderRequestDTO dto) {
        user.setGenderEnum(dto.getGenderEnum());

        return user;
    }

    public User requestDTOToExistEntityforAgeRange(User user, UserAgeRangeRequestDTO dto) {
        user.setAgeRangeEnum(dto.getAgeRangeEnum());

        return user;
    }

    public User requestDTOToExistEntityforName(User user, UserNameRequestDTO dto) {

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        return user;
    }


    @Override
    public List<User> dtoListToEntityList(List<UserDTO> userDTOS) {
        List<User> userList = new ArrayList<>();
        for (UserDTO userDTO : userDTOS) {
            User user = dtoToEntity(userDTO);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<UserDTO> entityListToDTOList(List<User> users) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = entityToDTO(user);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public PageDTO<UserDTO> pageEntityToPageDTO(Page<User> pageEntity) {
        PageDTO<UserDTO> pageDTO = new PageDTO<UserDTO>();
        List<User> entityList = pageEntity.toList();
        List<UserDTO> userDTOList = entityListToDTOList(entityList);
        pageDTO.setStart(pageEntity, userDTOList);

        return pageDTO;
    }*/

    @Mappings({@Mapping(source = "uuid", target = "value"),
            @Mapping(source = "username", target = "label")

    })
    AutoCompleteDTO entityToAutoCompleteDTO(User entity);

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
    void update(@MappingTarget User entity, User updateEntity);
}
