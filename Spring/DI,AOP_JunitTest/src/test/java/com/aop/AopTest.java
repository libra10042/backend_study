package com.aop;

import com.aop.step1.entity.Person;
import com.di.step11.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config2aop/config.xml")
public class AopTest {

    @Autowired
    ApplicationContext context;
    public static String loginName = "강감찬";

    @Test
    public void bean1(){

        // aop 실행 순서
        Person person = context.getBean(Person.class);

        //정상 실행, 파라미터 없음.
        System.out.println("\n### STEP0 : 클라이언트 person.print() 호출");
        person.print();

        // 정상 실행, 파라미터 1개
        System.out.println("\n### STEP0 : 클라이언트 person.getTot(\"1\")호출");
        Set<Map.Entry<String, Float>> entries = person.getTot("1").entrySet();
        System.out.println("\n### Client 결과 : " + entries);






    }

}
