package com.di.test;

import com.di.step5.Hello;
import com.di.step5.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/config5.xml")
public class JunitExam5 {

    @Autowired
    ApplicationContext context;

    @Test
    public void bean1(){
        Hello hello = context.getBean(Hello.class);
        hello.print("HELLO");


        List<String> list = hello.getNames();
        list.forEach(s -> System.out.println(s));

        System.out.println("-----------------------------");


        // 확장 for
        for(String value : list){
            System.out.println(value);
        }
        System.out.println("=======================");



        // iterator
        Iterator<String> lst = list.iterator();
        while(lst.hasNext()) {
            System.out.println(lst.next());
        }

        System.out.println("-----------------------");



        // enhencede for
        Map<String, Integer> ages = hello.getAges();
        for(Map.Entry<String, Integer> entry : ages.entrySet()){
            System.out.println("KEY : " + entry.getKey() + ", VALUE :" + entry.getValue());
        }


        // Iterator가 안되서 set(keyset)을 통해서 iterator를 얻음
        Iterator<String> keys = ages.keySet().iterator();
        while(keys.hasNext()){
            String key = keys.next();
            System.out.println("key : "+ key + ", value : " + ages.get(key));
        }

        // Lamda
        ages.forEach((key, value) -> System.out.println("key : " + key  + " | value :" + value));
        ages.entrySet().forEach(entry -> System.out.println("key : " + entry.getKey() + " | value : " + entry.getKey()));
        ages.keySet().forEach(key -> System.out.println("key : " + key));
        ages.values().forEach(value -> System.out.println("value :" + value));

        System.out.println("==============================");


        // ---------------------------------------------------------------


        // Set
        Set<String> hs = hello.getEmails();
        Iterator<String> hi = hs.iterator();

        // lamda
        hs.forEach(s -> System.out.println(s));


        while(hi.hasNext()) {
            System.out.println(hi.next());
        }

        for(String s : hs){
            System.out.println(s);
        }

        // ----------------------------------------------------------------

        // list 정렬
        List<Person> persons = hello.getPersons();


        System.out.println("----------- person name sor before --------------");

        persons.forEach(row ->System.out.println(row.getName() + " : " + row.getAge()));

        // class에 comparable이 정의되어야 사용이 가능하다.
        Collections.sort(persons);

        System.out.println("----------- person name sort after --------------");

        persons.forEach(row -> System.out.println(row.getName() +  " : "  + row.getAge()));

        // persons.forEach(row -> System.out.println(row.getName()));

        System.out.println("-----------------------------------------------");

    }

}
