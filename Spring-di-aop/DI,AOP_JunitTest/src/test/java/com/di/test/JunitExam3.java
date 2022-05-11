package com.di.test;

import com.di.step1.Hello;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JunitExam3 {

    ApplicationContext context;

    @Before
    public void init(){
        // 1. ApplicationContext 객체 생성
        context = new GenericXmlApplicationContext("classpath:config/config3.xml");
        System.out.println("@Before container create" + this);
    }


    @Test
    public void bean1(){
        System.out.println("@Test bean1 method :" + this);

        Hello hello = context.getBean(Hello.class);
        Hello hello2 = context.getBean(Hello.class);
        assertEquals(hello2, hello);

        hello.print("assertEquals check");

    }

    @After
    public void beforeTest(){
        System.out.println("@After Container end : " + this);
    }

    @BeforeClass
    public static void beforeClass(){
        System.out.println("@BeforeClass execute");

    }

    @AfterClass
    public static void afterClass(){
        System.out.println("@Afterclass execute");
    }


}
