package com.yd.dao;

import com.yd.model.Sales;
import java.util.List;

public interface SalesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sales record);

    Sales selectByPrimaryKey(Integer id);

    List<Sales> selectAll();

    int updateByPrimaryKey(Sales record);
}