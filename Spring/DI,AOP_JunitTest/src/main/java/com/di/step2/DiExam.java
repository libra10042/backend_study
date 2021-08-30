package com.di.step2;

import com.di.step1.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DiExam {
    public static void main(String[] args) throws ClassNotFoundException {

        ApplicationContext ctx = new GenericXmlApplicationContext("classpath:config/config.xml");


        // 3가지 방식중 하나 사용
//        Hello hello = (Hello) ctx.getBean("hello");

        Hello hello2 = ctx.getBean(Hello.class);


//        Hello hello3 = ctx.getBean("hello", Hello.class);




    }
}
