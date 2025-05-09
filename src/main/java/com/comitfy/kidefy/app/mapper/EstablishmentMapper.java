package com.comitfy.kidefy.app.mapper;
import com.comitfy.kidefy.app.entity.Establishment;
import com.comitfy.kidefy.util.common.BaseMapper;
import org.mapstruct.Mapper;
import com.comitfy.kidefy.app.dto.EstablishmentDTO;
import com.comitfy.kidefy.app.dto.request.EstablishmentRequestDTO;
@Mapper(componentModel = "spring")
public interface EstablishmentMapper extends BaseMapper<EstablishmentDTO,EstablishmentRequestDTO, Establishment> {

}