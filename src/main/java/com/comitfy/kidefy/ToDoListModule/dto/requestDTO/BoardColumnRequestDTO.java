package com.comitfy.kidefy.ToDoListModule.dto.requestDTO;

import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;

@Data
public class BoardColumnRequestDTO extends BaseDTO {


    private String label;

    private Integer position;

    private String color;

    private Boolean isVisible = Boolean.TRUE;
}
