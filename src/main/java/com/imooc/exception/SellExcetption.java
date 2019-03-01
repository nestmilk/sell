package com.imooc.exception;

import com.imooc.enums.ResultEnum;
import lombok.Getter;

import javax.xml.transform.Result;

/**
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/6/23 13:42
 */
@Getter
public class SellExcetption extends RuntimeException{
    private Integer code;

    public SellExcetption(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public SellExcetption(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
