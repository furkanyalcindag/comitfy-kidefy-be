package com.comitfy.kidefy.app.mapper;
import com.comitfy.kidefy.app.entity.Event;
import com.comitfy.kidefy.util.common.BaseMapper;
import org.mapstruct.Mapper;
import com.comitfy.kidefy.app.dto.EventDTO;
import com.comitfy.kidefy.app.dto.request.EventRequestDTO;
@Mapper(componentModel = "spring")
public interface EventMapper extends BaseMapper<EventDTO,EventRequestDTO, Event> {

}