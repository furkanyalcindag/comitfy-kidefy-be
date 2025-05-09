package com.comitfy.kidefy.app.dto;

import com.comitfy.kidefy.util.common.BaseDTO;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CityDTO extends BaseDTO {
  private String code;
  private String name;
}