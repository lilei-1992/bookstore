package com.yd.dao;

import com.yd.model.Supplier;
import java.util.List;

public interface SupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Supplier record);

    Supplier selectByPrimaryKey(Integer id);

    List<Supplier> selectAll();

    int updateByPrimaryKey(Supplier record);
}