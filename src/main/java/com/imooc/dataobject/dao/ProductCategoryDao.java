package com.imooc.dataobject.dao;

import com.imooc.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/10/21 22:43
 */
public class ProductCategoryDao {

    @Autowired
    private ProductCategoryMapper mapper;

    public Integer insertByMap(Map<String, Object> map){
        return mapper.insertByMap(map);
    }

}
