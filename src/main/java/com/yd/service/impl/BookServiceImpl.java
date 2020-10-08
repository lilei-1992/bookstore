package com.yd.service.impl;

import com.github.pagehelper.PageHelper;
import com.yd.dao.BookMapper;
import com.yd.dto.BookDto;
import com.yd.dto.BookSearchDto;
import com.yd.model.Book;
import com.yd.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: 李磊
 * Date: 2020/08/05 16:02
 * Description:
 * Version: V1.0
 */
@Service
public class BookServiceImpl implements IBookService {

    @Autowired(required = false)
    private BookMapper bookMapper;

    @Override
    public int addBook(Book book) {
        return bookMapper.insert(book);
    }

    @Override
    public List<BookDto> list(BookSearchDto bookSearchDto, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
//        return bookMapper.selectBooks(bookSearchDto);
        return null;
    }
}
