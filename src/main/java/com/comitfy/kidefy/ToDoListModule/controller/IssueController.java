package com.comitfy.kidefy.ToDoListModule.controller;

import com.comitfy.kidefy.ToDoListModule.dto.IssueDTO;
import com.comitfy.kidefy.ToDoListModule.dto.requestDTO.IssueRequestDTO;
import com.comitfy.kidefy.ToDoListModule.entity.Issue;
import com.comitfy.kidefy.ToDoListModule.mapper.IssueMapper;
import com.comitfy.kidefy.ToDoListModule.repository.IssueRepository;
import com.comitfy.kidefy.ToDoListModule.service.IssueService;
import com.comitfy.kidefy.ToDoListModule.specification.IssueSpecification;
import com.comitfy.kidefy.util.common.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("kanban/issue")
public class IssueController extends BaseCrudController<IssueDTO, IssueRequestDTO, Issue, IssueRepository, IssueMapper, IssueSpecification, IssueService> {

    @Autowired
    IssueService issueService;

    @Autowired
    IssueMapper issueMapper;

    @Override
    protected IssueService getService() {
        return issueService;
    }

    @Override
    protected IssueMapper getMapper() {
        return issueMapper;
    }


    @PostMapping("create-issue")
    public ResponseEntity<IssueDTO> save(@RequestBody IssueRequestDTO body) {

        return new ResponseEntity<>(getService().save(body), HttpStatus.CREATED);
    }


    @PutMapping("update-issue/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody IssueRequestDTO body) {
        IssueDTO issueDTO = getService().update(id, body);

        if (issueDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>("Object with the id " + id + " was updated.", HttpStatus.OK);
        }

    }


    @PutMapping("update-column/{id}")
    public ResponseEntity<String> updateColumn(@PathVariable UUID id, @RequestBody IssueRequestDTO body) {
        IssueDTO issueDTO = getService().updateColumn(id, body);

        if (issueDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>("Object with the id " + id + " was updated.", HttpStatus.OK);
        }

    }


    @PutMapping("update-user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @RequestBody IssueRequestDTO body) {
        IssueDTO issueDTO = getService().updateUser(id, body);

        if (issueDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>("Object with the id " + id + " was updated.", HttpStatus.OK);
        }

    }




}
