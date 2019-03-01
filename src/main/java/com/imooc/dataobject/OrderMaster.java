package com.imooc.dataobject;

import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/6/17 23:04
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    @Id
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    /**
     *买家微信openid
     */
    private String buyerOpenid;
    private BigDecimal orderAmount;
    /**
     *订单状态,默认为0新下单
     */
    private Integer orderStatus =OrderStatusEnum.NEW.getCode();
    /**
     *支付状态，默认为0未支付
     */
    private Integer payStatus=PayStatusEnum.WAIT.getCode();

    private Date createTime;
    private Date updateTime;


}

