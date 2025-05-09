package com.comitfy.kidefy.app.dto;
import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;
@Data
public class EstablishmentDTO extends BaseDTO {

  private String name;
  private String address;
  private String logo;
  private CityDTO city;
}