package com.comitfy.kidefy.app.entity;

import com.comitfy.kidefy.util.dbUtil.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "module_uuid"
        )
)
public class Module extends BaseEntity {

        @Column
        private long topId;

        @Column
        private String title;

        @Column
        private String icon;

        @Column
        private String targetPath;

        @Column
        private Long manualId;


}
