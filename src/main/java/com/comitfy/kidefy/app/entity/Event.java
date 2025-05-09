package com.comitfy.kidefy.app.entity;

import com.comitfy.kidefy.util.dbUtil.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AttributeOverride(
    name = "uuid",
    column = @Column(
        name = "event_uuid"
    )
)
public class Event extends BaseEntity {

  @Column
  private String name;
  @Column
  private String description;
  @Column
  private String eventImage;
  @Column
  private LocalDate eventDate;
  @Column
  private LocalTime eventTime;
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "establishment_id")
  private Establishment establishment;
  @Column
  private BigDecimal price;
  @Column
  private Integer quota;
  @Column
  private String tags;
}