package com.imooc.service;

import com.imooc.dto.OrderDTO;

/**
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/10/20 16:08
 */
public interface PushMessageService {
    /**
     *订单状态变更消息
     */
    void orderStatus(OrderDTO orderDTO);
}
