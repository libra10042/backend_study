package com.di.test;

import com.di.step10.Config;
import com.di.step10.Hello;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class})
public class JunitExam10 {

    @Autowired
    ApplicationContext context;

    @Test
    public void bean1(){
        Hello hello = context.getBean(Hello.class);
        hello.print("hello");
    }
}
