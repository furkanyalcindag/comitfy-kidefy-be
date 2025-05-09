package com.comitfy.kidefy.app.mapper;

import com.comitfy.kidefy.app.dto.AutoCompleteDTOwithId;
import com.comitfy.kidefy.app.dto.ModuleDTO;
import com.comitfy.kidefy.app.dto.request.ModuleRequestDTO;
import com.comitfy.kidefy.app.entity.Module;
import com.comitfy.kidefy.util.common.AutoCompleteDTO;
import com.comitfy.kidefy.util.common.BaseMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ModuleMapper extends BaseMapper<ModuleDTO, ModuleRequestDTO, Module> {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "uuid", ignore = true),
    })

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Module entity, Module updateEntity);

    @Mappings({@Mapping(source = "uuid", target = "value"),
            @Mapping(source = "title", target = "label")

    })

    AutoCompleteDTO entityToAutoCompleteDTO(Module entity);

    @Mappings({@Mapping(source = "id", target = "value"),
            @Mapping(source = "title", target = "label")

    })
    AutoCompleteDTOwithId entityToAutoCompleteDTOById(Module entity);


}
