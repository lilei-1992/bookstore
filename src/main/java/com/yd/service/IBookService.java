package com.yd.service;

import com.yd.dto.BookDto;
import com.yd.dto.BookSearchDto;
import com.yd.model.Book;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: 李磊
 * Date: 2020/08/05 16:00
 * Description:
 * Version: V1.0
 */
public interface IBookService {
    int addBook(Book book);

    List<BookDto> list(BookSearchDto bookSearchDto, int pageNum, int pageSize);

}
