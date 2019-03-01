package com.imooc.service;

import com.imooc.dataobject.SellerInfo;

/**
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/10/17 11:26
 */
public interface SellerService {

    SellerInfo findSellerInfoByOpenid (String openid);
}
