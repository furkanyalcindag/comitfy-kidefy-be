package com.comitfy.kidefy.app.service;
import com.comitfy.kidefy.app.dto.CityDTO;
import com.comitfy.kidefy.app.dto.request.CityRequestDTO;
import com.comitfy.kidefy.app.entity.City;
import com.comitfy.kidefy.app.mapper.CityMapper;
import com.comitfy.kidefy.app.repository.CityRepository;
import com.comitfy.kidefy.app.specification.CitySpecification;
import com.comitfy.kidefy.util.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CityService extends BaseService<CityDTO, CityRequestDTO, City, CityRepository, CityMapper, CitySpecification> {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    CityMapper cityMapper;

    @Autowired
    CitySpecification citySpecification;

    @Override
    public CityRepository getRepository() {
        return cityRepository;
    }

    @Override
    public CityMapper getMapper() {
        return cityMapper;
    }

    @Override
    public  CitySpecification getSpecification() {
        return citySpecification;
}
}