package com.comitfy.kidefy.app.dto;
import com.comitfy.kidefy.util.common.BaseDTO;
import jakarta.persistence.Column;
import lombok.Data;
@Data
public class BlogDTO extends BaseDTO {
  private String title;
  private String content;
  private String image;
}