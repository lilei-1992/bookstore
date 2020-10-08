package com.yd.controller;

import com.yd.dto.BookSearchDto;
import com.yd.model.Book;
import com.yd.service.IBookService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Author: 李磊
 * Date: 2020/08/05 15:57
 * Description:
 * Version: V1.0
 */
@Controller
@RequestMapping("/api/book")
public class BookController extends BaseController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @PostMapping()
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        if (StringUtils.isBlank(book.getName())) {
            return error("name不能为空");
        }
        if (bookService.addBook(book) <= 0) {
            return error("服务器错误");
    }
        return success(null);
    }

    @PutMapping()
    public ResponseEntity<?> list(@RequestBody BookSearchDto bookSearchDto,
                                  @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return success(bookService.list(bookSearchDto, pageNum, pageSize));
    }

    @GetMapping()
    public void get(HttpServletRequest request){
        System.out.println("开始");
        System.out.println(request.getContextPath());
        System.out.println(request.getServletContext().getRealPath("/"));
//        System.out.println(ClassLoader.getSystemClassLoader().getResource(""));
//        System.out.println(ClassLoader.getSystemClassLoader().getResource("application.yml").getPath());

    }
}
