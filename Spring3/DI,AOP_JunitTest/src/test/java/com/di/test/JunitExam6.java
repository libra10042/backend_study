package com.di.test;

import com.di.step6.Hello;
import com.di.step6.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/config6.xml")
public class JunitExam6 {

    @Autowired
    ApplicationContext context;


    @Test
    public void bean1(){

        Hello hello = context.getBean(Hello.class);

        List<Person> persons = hello.getPersons();

        System.out.println("==== before sort ====");
        persons.forEach(p -> hello.print(p.getName() + "|" + p.getAge() + "|"  +p.getClass()));

        // 이름
        Collections.sort(persons);

        System.out.println("==== after sort ===");
        persons.forEach(p -> hello.print(p.getName() + "|" + p.getAge() + "|" + p.getClass()));




    }
}
