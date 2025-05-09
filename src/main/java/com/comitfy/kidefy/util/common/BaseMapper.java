package com.comitfy.kidefy.util.common;


import com.comitfy.kidefy.util.dbUtil.BaseEntity;
import org.mapstruct.*;

import java.util.List;

public interface BaseMapper<DTO extends BaseDTO, RequestDTO extends BaseDTO, Entity extends BaseEntity> {

    DTO entityToDTO(Entity entity);

    Entity entitytoEntity(Entity entity);

    Entity dtoToEntity(DTO dto);

    Entity requestDTOToEntity(RequestDTO dto);

    //Entity requestDTOToExistEntity(Entity entity, RequestDTO dto);*/

    List<Entity> dtoListToEntityList(List<DTO> dtoList);

    List<DTO> entityListToDTOList(List<Entity> entityList);


    void update(@MappingTarget Entity entity, Entity updateEntity);



    AutoCompleteDTO entityToAutoCompleteDTO(Entity entity);

    //PageDTO<DTO> pageEntityToPageDTO(Page<Entity> pageEntity);


}
