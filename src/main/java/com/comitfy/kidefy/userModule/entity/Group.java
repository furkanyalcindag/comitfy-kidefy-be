package com.comitfy.kidefy.userModule.entity;


import com.comitfy.kidefy.util.dbUtil.BaseEntity;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "group")
public class Group extends BaseEntity {
    @Column
    private String name;
    @Column
    private String description;


}
