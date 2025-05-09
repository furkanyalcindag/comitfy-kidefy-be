package com.comitfy.kidefy.ToDoListModule.entity;

import com.comitfy.kidefy.util.dbUtil.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "board_column_uuid"
        )
)
public class BoardColumn extends BaseEntity {

    @Column(unique = true)
    private String label;
    @Column
    private Integer position;
    @Column
    private String color;
    @Column
    private Boolean isVisible = Boolean.TRUE;
}
