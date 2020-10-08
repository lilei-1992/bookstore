package com.yd.dao;

import com.yd.model.Purchase;
import java.util.List;

public interface PurchaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Purchase record);

    Purchase selectByPrimaryKey(Integer id);

    List<Purchase> selectAll();

    int updateByPrimaryKey(Purchase record);
}