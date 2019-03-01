package com.imooc.form;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/10/12 15:31
 */

@Data
public class ProductForm {

    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer categoryType;

    private Integer page;

}
