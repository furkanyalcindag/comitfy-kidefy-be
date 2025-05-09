package com.comitfy.kidefy.ToDoListModule.service;

import com.comitfy.kidefy.ToDoListModule.dto.BoardColumnDTO;
import com.comitfy.kidefy.ToDoListModule.dto.requestDTO.BoardColumnRequestDTO;
import com.comitfy.kidefy.ToDoListModule.entity.BoardColumn;
import com.comitfy.kidefy.ToDoListModule.mapper.BoardColumnMapper;
import com.comitfy.kidefy.ToDoListModule.repository.BoardColumnRepository;
import com.comitfy.kidefy.ToDoListModule.specification.BoardColumnSpecification;
import com.comitfy.kidefy.util.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardColumnService extends BaseService<BoardColumnDTO, BoardColumnRequestDTO, BoardColumn, BoardColumnRepository, BoardColumnMapper, BoardColumnSpecification> {

    @Autowired
    BoardColumnMapper boardColumnMapper;

    @Autowired
    BoardColumnRepository boardColumnRepository;

    @Autowired
    BoardColumnSpecification boardColumnSpecification;


    @Override
    public BoardColumnRepository getRepository() {
        return boardColumnRepository;
    }

    @Override
    public BoardColumnMapper getMapper() {
        return boardColumnMapper;
    }

    @Override
    public BoardColumnSpecification getSpecification() {
        return boardColumnSpecification;
    }
}
