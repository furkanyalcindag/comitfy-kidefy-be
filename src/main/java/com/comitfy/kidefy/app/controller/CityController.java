package com.comitfy.kidefy.app.controller;
import com.comitfy.kidefy.app.dto.CityDTO;
import com.comitfy.kidefy.app.dto.request.CityRequestDTO;
import com.comitfy.kidefy.app.entity.City;
import com.comitfy.kidefy.app.mapper.CityMapper;
import com.comitfy.kidefy.app.repository.CityRepository;
import com.comitfy.kidefy.app.service.CityService;
import com.comitfy.kidefy.app.specification.CitySpecification;
import com.comitfy.kidefy.util.common.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("city")
public class CityController extends BaseCrudController<CityDTO, CityRequestDTO, City, CityRepository, CityMapper, CitySpecification, CityService> {
    @Autowired
    CityMapper cityMapper;

    @Autowired
    CityService cityService;

    @Override
    public CityService getService() {
        return cityService;
    }

    @Override
    public CityMapper getMapper() {
        return cityMapper;
    }

}