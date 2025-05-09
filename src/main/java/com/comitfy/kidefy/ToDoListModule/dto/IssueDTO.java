package com.comitfy.kidefy.ToDoListModule.dto;

import com.comitfy.kidefy.ToDoListModule.model.enums.PriorityEnum;
import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;

@Data
public class IssueDTO extends BaseDTO {

    public String name;

    public String description;

    public PriorityEnum priority;

    public String tags;

    public String user;

}
