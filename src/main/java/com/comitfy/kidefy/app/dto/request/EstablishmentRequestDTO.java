package com.comitfy.kidefy.app.dto.request;

import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;

@Data
public class EstablishmentRequestDTO extends BaseDTO {

  private String name;
  private String address;
  private String logo;
  private CityRequestDTO city;

}