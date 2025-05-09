package com.comitfy.kidefy.app.entity;

import com.comitfy.kidefy.util.dbUtil.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
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
        name = "blog_uuid"
    )
)
public class Blog extends BaseEntity {

    @Column
    private String title;

    @Lob
    @Column(columnDefinition = "text")
    private String content;

    @Column
    private String image;

}