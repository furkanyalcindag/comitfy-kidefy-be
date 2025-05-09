package com.comitfy.kidefy.app.controller;
import com.comitfy.kidefy.app.dto.EventDTO;
import com.comitfy.kidefy.app.dto.request.EventRequestDTO;
import com.comitfy.kidefy.app.entity.Event;
import com.comitfy.kidefy.app.mapper.EventMapper;
import com.comitfy.kidefy.app.repository.EventRepository;
import com.comitfy.kidefy.app.service.EventService;
import com.comitfy.kidefy.app.specification.EventSpecification;
import com.comitfy.kidefy.util.common.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("event")
public class EventController extends BaseCrudController<EventDTO, EventRequestDTO, Event, EventRepository, EventMapper, EventSpecification, EventService> {
    @Autowired
    EventMapper eventMapper;

    @Autowired
    EventService eventService;

    @Override
    public EventService getService() {
        return eventService;
    }

    @Override
    public EventMapper getMapper() {
        return eventMapper;
    }

}