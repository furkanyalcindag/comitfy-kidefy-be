package com.comitfy.kidefy.app.service;
import com.comitfy.kidefy.app.dto.EventDTO;
import com.comitfy.kidefy.app.dto.request.EventRequestDTO;
import com.comitfy.kidefy.app.entity.Event;
import com.comitfy.kidefy.app.mapper.EventMapper;
import com.comitfy.kidefy.app.repository.EventRepository;
import com.comitfy.kidefy.app.specification.EventSpecification;
import com.comitfy.kidefy.util.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EventService extends BaseService<EventDTO, EventRequestDTO, Event, EventRepository, EventMapper, EventSpecification> {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventMapper eventMapper;

    @Autowired
    EventSpecification eventSpecification;

    @Override
    public EventRepository getRepository() {
        return eventRepository;
    }

    @Override
    public EventMapper getMapper() {
        return eventMapper;
    }

    @Override
    public  EventSpecification getSpecification() {
        return eventSpecification;
}
}