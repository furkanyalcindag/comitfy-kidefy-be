package com.comitfy.kidefy.app.controller;
import com.comitfy.kidefy.app.dto.BlogDTO;
import com.comitfy.kidefy.app.dto.request.BlogRequestDTO;
import com.comitfy.kidefy.app.entity.Blog;
import com.comitfy.kidefy.app.mapper.BlogMapper;
import com.comitfy.kidefy.app.repository.BlogRepository;
import com.comitfy.kidefy.app.service.BlogService;
import com.comitfy.kidefy.app.specification.BlogSpecification;
import com.comitfy.kidefy.util.common.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("blog")
public class BlogController extends BaseCrudController<BlogDTO, BlogRequestDTO, Blog, BlogRepository, BlogMapper, BlogSpecification, BlogService> {
    @Autowired
    BlogMapper blogMapper;

    @Autowired
    BlogService blogService;

    @Override
    public BlogService getService() {
        return blogService;
    }

    @Override
    public BlogMapper getMapper() {
        return blogMapper;
    }

}