package com.comitfy.kidefy.app.mapper;

import com.comitfy.kidefy.app.dto.AuthorizationsDTO;
import com.comitfy.kidefy.app.dto.AutoCompleteDTOwithId;
import com.comitfy.kidefy.app.dto.request.AuthorizationsRequestDTO;
import com.comitfy.kidefy.app.entity.Authorizations;
import com.comitfy.kidefy.util.common.AutoCompleteDTO;
import com.comitfy.kidefy.util.common.BaseMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AuthorizationsMapper extends BaseMapper<AuthorizationsDTO, AuthorizationsRequestDTO, Authorizations> {
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
    void update(@MappingTarget Authorizations entity, Authorizations updateEntity);

    @Mappings({@Mapping(source = "uuid", target = "value"),
            @Mapping(source = "authName", target = "label")

    })
    AutoCompleteDTO entityToAutoCompleteDTO(Authorizations entity);

    @Mappings({@Mapping(source = "id", target = "value"),
            @Mapping(source = "authName", target = "label")

    })
    AutoCompleteDTOwithId entityToAutoCompleteDTOById(Authorizations entity);

}
