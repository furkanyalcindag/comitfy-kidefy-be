package com.comitfy.kidefy.app.dto.request;
import com.comitfy.kidefy.util.common.BaseDTO;
import lombok.Data;
@Data
public class BlogRequestDTO extends BaseDTO {
  private String title;
  private String content;
  private String image;
}