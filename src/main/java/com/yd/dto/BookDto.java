package com.yd.dto;

import com.yd.model.Book;

/**
 * Created with IntelliJ IDEA.
 * Author: 李磊
 * Date: 2020/08/05 17:35
 * Description:
 * Version: V1.0
 */
public class BookDto extends Book {
    private Integer orderQuantity;
    private Integer salesQuantity;
    private Integer total;

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Integer getSalesQuantity() {
        return salesQuantity;
    }

    public void setSalesQuantity(Integer salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
