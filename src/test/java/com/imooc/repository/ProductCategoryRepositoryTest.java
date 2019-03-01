package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;
    @Test
    public void findByIdTest(){
      ProductCategory productCategory= repository.findById(1).orElse(null);
      System.out.println("打印结果: "+productCategory);
    }

    @Test
    public void saveTest(){
        ProductCategory productCategory=new ProductCategory("女生最爱", 3);
        ProductCategory result=repository.save(productCategory);
        Assert.assertNotNull(result);
        //Assert.assertNotEquals(result,null);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list= Arrays.asList(2,3,4);
        List<ProductCategory> result=repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}