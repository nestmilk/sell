package com.imooc.enums;

import lombok.Getter;

/**
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/6/18 7:57
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {
    NEW(0,"新订单"),
    FINISHED(1,"已完结"),
    CANCEL(2,"已取消")
    ;
    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
