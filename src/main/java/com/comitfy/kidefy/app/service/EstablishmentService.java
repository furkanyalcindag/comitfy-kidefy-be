package com.comitfy.kidefy.app.service;
import com.comitfy.kidefy.app.dto.EstablishmentDTO;
import com.comitfy.kidefy.app.dto.request.EstablishmentRequestDTO;
import com.comitfy.kidefy.app.entity.Establishment;
import com.comitfy.kidefy.app.mapper.EstablishmentMapper;
import com.comitfy.kidefy.app.repository.EstablishmentRepository;
import com.comitfy.kidefy.app.specification.EstablishmentSpecification;
import com.comitfy.kidefy.util.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EstablishmentService extends BaseService<EstablishmentDTO, EstablishmentRequestDTO, Establishment, EstablishmentRepository, EstablishmentMapper, EstablishmentSpecification> {
    @Autowired
    EstablishmentRepository establishmentRepository;

    @Autowired
    EstablishmentMapper establishmentMapper;

    @Autowired
    EstablishmentSpecification establishmentSpecification;

    @Override
    public EstablishmentRepository getRepository() {
        return establishmentRepository;
    }

    @Override
    public EstablishmentMapper getMapper() {
        return establishmentMapper;
    }

    @Override
    public  EstablishmentSpecification getSpecification() {
        return establishmentSpecification;
}
}