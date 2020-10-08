package com.yd.dto;

/**
 * Created with IntelliJ IDEA.
 * Author: 李磊
 * Date: 2020/08/05 16:15
 * Description:
 * Version: V1.0
 */
public class BookSearchDto {
    private String name;
    private String publisher;
    private String author;
    private String supplier;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }
}
