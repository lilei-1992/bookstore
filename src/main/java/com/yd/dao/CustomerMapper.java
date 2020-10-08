package com.yd.dao;

import com.yd.model.Customer;
import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    Customer selectByPrimaryKey(Integer id);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);
}