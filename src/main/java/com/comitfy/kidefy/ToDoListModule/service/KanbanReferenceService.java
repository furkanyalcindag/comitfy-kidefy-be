package com.comitfy.kidefy.ToDoListModule.service;

import com.comitfy.kidefy.ToDoListModule.model.enums.PriorityDTO;
import com.comitfy.kidefy.ToDoListModule.model.enums.PriorityEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class KanbanReferenceService {


    public List<PriorityDTO> getPriorityEnum() {

        List<PriorityDTO> priorityDTOList = new ArrayList<>();

        List<PriorityEnum> list = Stream.of(PriorityEnum.values())

                .collect(Collectors.toList());

        for (PriorityEnum priorityEnum: list) {

            PriorityDTO priorityDTO = new PriorityDTO();
            priorityDTO = priorityEnum.toDTO();
            priorityDTOList.add(priorityDTO);

        }


        //list.remove(ProposalStatusEnum.APPROVED);

        return priorityDTOList;

    }



}
