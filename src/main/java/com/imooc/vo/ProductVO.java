package com.imooc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品(包含类目)
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/6/17 15:06
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = -3555231358992190972L;
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
