package com.yd.dao;

import com.yd.model.BookSales;
import java.util.List;

public interface BookSalesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookSales record);

    BookSales selectByPrimaryKey(Integer id);

    List<BookSales> selectAll();

    int updateByPrimaryKey(BookSales record);
}