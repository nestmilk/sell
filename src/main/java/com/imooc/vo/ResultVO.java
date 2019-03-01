package com.imooc.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/6/15 0:20
 */
@Data
public class ResultVO<T> implements Serializable {


    private static final long serialVersionUID = -8349181922768084604L;
    /**
     * 错误码
     */
    private Integer code;
    private String msg;
    private T data;
}
