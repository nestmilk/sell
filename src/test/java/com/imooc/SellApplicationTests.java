package com.imooc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void genUniqueKey(){
        Random random=new Random();
        /**生成6位随机数*/
        Integer number=random.nextInt(900000)+100000;
        System.out.println( System.currentTimeMillis());
        System.out.println( String.valueOf(number));
        System.out.println( System.currentTimeMillis()+String.valueOf(number));
    }

}
