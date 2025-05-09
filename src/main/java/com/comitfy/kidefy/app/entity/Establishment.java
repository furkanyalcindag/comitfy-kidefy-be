package com.comitfy.kidefy.app.entity;

import com.comitfy.kidefy.util.dbUtil.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
        name = "establishment_uuid"
    )
)
public class Establishment extends BaseEntity {

  @Column
  private String name;

  @Column
  private String address;

  @Column
  private String logo;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "city_id")
  private City city;
  @Column
  private Boolean isActive;

}