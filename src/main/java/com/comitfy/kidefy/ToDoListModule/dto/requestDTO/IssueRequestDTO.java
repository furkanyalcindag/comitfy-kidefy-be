package com.comitfy.kidefy.ToDoListModule.dto.requestDTO;

import com.comitfy.kidefy.ToDoListModule.model.enums.PriorityEnum;
import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class IssueRequestDTO extends BaseDTO {

    public String name;

    public String description;

    public PriorityEnum priority;

    public String tags;

    public UUID userUUID;

    public UUID boardColumnUUID;

}
