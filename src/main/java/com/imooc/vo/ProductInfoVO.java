package com.imooc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品详情
 *
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/6/17 15:13
 */
@Data
public class ProductInfoVO implements Serializable {

    private static final long serialVersionUID = 6179894408250932674L;
    @JsonProperty("id")
    private String productId;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("description")
    private String productDescription;
    @JsonProperty("icon")
    private String productIcon;
}
