package com.comitfy.kidefy.app.dto;

import com.comitfy.kidefy.util.common.BaseDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class EventDTO extends BaseDTO {
  private String name;
  private String description;
  private String eventImage;
  private LocalDate eventStartDate;
  private LocalTime eventStartTime;
  private LocalDate eventEndDate;
  private LocalTime eventEndTime;
  private EstablishmentDTO establishment;
  private BigDecimal price;
  private Integer quota;
  private String tags;
  private Boolean isActive;
}