package com.di.test;

import com.di.step1.Hello;
import com.di.step1.Printer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/config4.xml")
public class JunitExam4_2 {

    @Autowired
    ApplicationContext context;

    @Test
    public void bean1(){
        System.out.println("container : " + context);
        System.out.println("bean1() : " + this );

        // assertEquals() 값 비교.
        Hello hello = context.getBean(Hello.class);
        assertEquals("SPRING", hello.getName());
        hello.print("hello");
    }

    @Test
    public void bean2(){
        System.out.println("container : " + context);
        System.out.println("bean2() : " + this);

        Printer printer = context.getBean(Printer.class);
        Printer printer2 = context.getBean("printer : ", Printer.class);  // 상태를 변경할 수 없다.
        // assertSame 객체 비교
        assertSame(printer, printer2);
    }

}


