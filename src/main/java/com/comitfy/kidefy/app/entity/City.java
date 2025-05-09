package com.comitfy.kidefy.app.entity;

import com.comitfy.kidefy.util.dbUtil.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AttributeOverride(
    name = "uuid",
    column = @Column(
        name = "city_uuid"
    )
)
public class City extends BaseEntity {

    @Column
    private String code;
    @Column
    private String name;

}