package com.comitfy.kidefy.userModule.entity;


import com.comitfy.kidefy.util.dbUtil.BaseEntity;
import lombok.Data;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table
public class Role extends BaseEntity {
    @Column(unique = true)
    private String name;
    @Column
    private String description;

    @Column
    private Boolean isStaff;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    @ElementCollection
    private List<Long> moduleIds;

    @ElementCollection
    private List<Long> authorizationsIds;

    public Role() {

    }



}
