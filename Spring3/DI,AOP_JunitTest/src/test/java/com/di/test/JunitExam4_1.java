package com.di.test;

import com.di.step1.Hello;
import com.di.step1.Printer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JunitExam4_1 {
    static ApplicationContext context;

    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext("classpath:config/config4.xml");
        System.out.println(context);
    }

    @Test
    public void bean1(){
        System.out.println(context);
        System.out.println("bean1 method : " + this);

        Hello hello = context.getBean("hello", Hello.class);
        assertEquals("SPRING", hello.getName());

        Hello hello1 = context.getBean("hello1", Hello.class);

        assertSame(hello, hello1);

        hello.print("hello : " + hello);
        System.out.println("----------------");
    }


    @Test
    public void bean2(){
        System.out.println(context);
        System.out.println("bean2 method :  " + this);


        Printer printer = (Printer) context.getBean("printer");
        Printer printer2 = context.getBean(Printer.class);

        assertSame(printer, printer2);
    }
}
