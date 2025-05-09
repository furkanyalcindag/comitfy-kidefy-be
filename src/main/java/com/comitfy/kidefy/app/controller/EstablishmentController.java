package com.comitfy.kidefy.app.controller;
import com.comitfy.kidefy.app.dto.EstablishmentDTO;
import com.comitfy.kidefy.app.dto.request.EstablishmentRequestDTO;
import com.comitfy.kidefy.app.entity.Establishment;
import com.comitfy.kidefy.app.mapper.EstablishmentMapper;
import com.comitfy.kidefy.app.repository.EstablishmentRepository;
import com.comitfy.kidefy.app.service.EstablishmentService;
import com.comitfy.kidefy.app.specification.EstablishmentSpecification;
import com.comitfy.kidefy.util.common.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("establishment")
public class EstablishmentController extends BaseCrudController<EstablishmentDTO, EstablishmentRequestDTO, Establishment, EstablishmentRepository, EstablishmentMapper, EstablishmentSpecification, EstablishmentService> {
    @Autowired
    EstablishmentMapper establishmentMapper;

    @Autowired
    EstablishmentService establishmentService;

    @Override
    public EstablishmentService getService() {
        return establishmentService;
    }

    @Override
    public EstablishmentMapper getMapper() {
        return establishmentMapper;
    }

}