package com.comitfy.kidefy.app.mapper;
import com.comitfy.kidefy.app.entity.City;
import com.comitfy.kidefy.util.common.BaseMapper;
import org.mapstruct.Mapper;
import com.comitfy.kidefy.app.dto.CityDTO;
import com.comitfy.kidefy.app.dto.request.CityRequestDTO;
@Mapper(componentModel = "spring")
public interface CityMapper extends BaseMapper<CityDTO,CityRequestDTO, City> {

}