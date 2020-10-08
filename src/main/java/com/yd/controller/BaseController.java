package com.yd.controller;

import com.github.pagehelper.Page;
import com.yd.utils.Paged;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    protected ResponseEntity<?> success(Object data) {
        return getResponseEntity(HttpStatus.OK, "操作成功", data);
    }

    protected ResponseEntity<?> error(HttpStatus statusCode, String msg) {
        return getResponseEntity(statusCode, msg, null);
    }

    protected ResponseEntity<?> error(String msg) {
        return getResponseEntity(HttpStatus.BAD_REQUEST, msg, null);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	protected ResponseEntity<?> getResponseEntity(HttpStatus code, String msg, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code.value());
        map.put("message", msg);
        if(data instanceof Page) {
            data = new Paged((Page)data);
        }
        if(data != null) {
            map.put("data", data);
        }
        return new ResponseEntity<Object>(map, code);
    }

}
