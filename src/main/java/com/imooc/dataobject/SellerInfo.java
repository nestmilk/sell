package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/10/17 0:14
 */
@Data
@Entity
public class SellerInfo {
    @Id
    private String id;
    private String username;
    private String password;
    private String openid;
//    private Date createTime;
//    private Date updateTime;
}
