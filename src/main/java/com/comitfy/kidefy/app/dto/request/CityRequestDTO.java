package com.comitfy.kidefy.app.dto.request;
import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;
@Data
public class CityRequestDTO extends BaseDTO {
  private String code;
  private String name;
}