package com.comitfy.kidefy.app.mapper;
import com.comitfy.kidefy.app.entity.Blog;
import com.comitfy.kidefy.util.common.BaseMapper;
import org.mapstruct.Mapper;
import com.comitfy.kidefy.app.dto.BlogDTO;
import com.comitfy.kidefy.app.dto.request.BlogRequestDTO;
@Mapper(componentModel = "spring")
public interface BlogMapper extends BaseMapper<BlogDTO,BlogRequestDTO, Blog> {

}