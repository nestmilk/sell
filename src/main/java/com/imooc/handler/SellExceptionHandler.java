package com.imooc.handler;

import com.imooc.exception.SellExcetption;
import com.imooc.utils.ResultVOUtil;
import com.imooc.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/10/21 12:10
 */
@ControllerAdvice
public class SellExceptionHandler {

    @ExceptionHandler(value= SellExcetption.class)
    @ResponseBody
    public ResultVO handleSellerException(SellExcetption e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
