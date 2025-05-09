package com.comitfy.kidefy.app.service;
import com.comitfy.kidefy.app.dto.BlogDTO;
import com.comitfy.kidefy.app.dto.request.BlogRequestDTO;
import com.comitfy.kidefy.app.entity.Blog;
import com.comitfy.kidefy.app.mapper.BlogMapper;
import com.comitfy.kidefy.app.repository.BlogRepository;
import com.comitfy.kidefy.app.specification.BlogSpecification;
import com.comitfy.kidefy.util.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BlogService extends BaseService<BlogDTO, BlogRequestDTO, Blog, BlogRepository, BlogMapper, BlogSpecification> {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    BlogSpecification blogSpecification;

    @Override
    public BlogRepository getRepository() {
        return blogRepository;
    }

    @Override
    public BlogMapper getMapper() {
        return blogMapper;
    }

    @Override
    public  BlogSpecification getSpecification() {
        return blogSpecification;
}
}