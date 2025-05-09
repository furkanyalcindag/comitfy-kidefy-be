package com.comitfy.kidefy.ToDoListModule.controller;

import com.comitfy.kidefy.ToDoListModule.dto.BoardColumnDTO;
import com.comitfy.kidefy.ToDoListModule.dto.requestDTO.BoardColumnRequestDTO;
import com.comitfy.kidefy.ToDoListModule.entity.BoardColumn;
import com.comitfy.kidefy.ToDoListModule.mapper.BoardColumnMapper;
import com.comitfy.kidefy.ToDoListModule.repository.BoardColumnRepository;
import com.comitfy.kidefy.ToDoListModule.service.BoardColumnService;
import com.comitfy.kidefy.ToDoListModule.specification.BoardColumnSpecification;
import com.comitfy.kidefy.util.common.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kanban/board-column")
public class BoardColumnController extends BaseCrudController<BoardColumnDTO, BoardColumnRequestDTO, BoardColumn, BoardColumnRepository, BoardColumnMapper, BoardColumnSpecification, BoardColumnService> {

    @Autowired
    BoardColumnService boardColumnService;

    @Autowired
    BoardColumnMapper boardColumnMapper;

    @Override
    protected BoardColumnService getService() {
        return boardColumnService;
    }

    @Override
    protected BoardColumnMapper getMapper() {
        return boardColumnMapper;
    }
}
