package com.imooc.dto;

import lombok.Data;

/**
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/6/23 17:38
 */
@Data
public class CartDTO {
    /**
     *商品Id
     */
    private String productId;
    /**
     *商品数量
     */
    private Integer productQuantitity;

    public CartDTO(String productId, Integer productQuantitity) {
        this.productId = productId;
        this.productQuantitity = productQuantitity;
    }
}
