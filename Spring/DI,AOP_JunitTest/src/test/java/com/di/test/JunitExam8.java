package com.di.test;

import com.di.step8.Hello;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/config8.xml")
public class JunitExam8 {

    @Autowired
    ApplicationContext context;

    @Test
    public void bean1(){

//        Autowired(required=false) 테스트


//        Hello hello = context.getBean(Hello.class);
        Hello hello = (Hello) context.getBean("hello");


//        hello.print("hello");
        hello.toString();
    }

}
