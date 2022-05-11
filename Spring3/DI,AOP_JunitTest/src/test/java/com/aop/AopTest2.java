package com.aop;

import com.aop.step2.entity.Business02Proc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config2aop/config2.xml")
public class AopTest2 {

    @Autowired
    ApplicationContext context;


    @Test
    public void bean1(){

        /*
        * excution(public * com.di.entitiy...*.*(..))
        * com.di.entity 패키지의 모든 클래스에 적용된다. 그리고 '..' 를 사용해서 서브패키지에 모든 클래스까지 포함한다.
        *
        * */

        /*
        Business01Proc bisComponent01 = context.getBean(Business01Proc.class);
        System.out.println(bisComponent01.cmdComponent01());
        System.out.println(bisComponent01.cmdComponent02("Test", 100, 300);

         */


        Business02Proc bisComponent02 = context.getBean(Business02Proc.class);

        System.out.println(bisComponent02.cdComponent03());
        System.out.println(bisComponent02.cdComponent04("Test"));

        bisComponent02.printThrowException();


//        try{
//            bisComponent02.printThrowException();
//        }catch(IllegalArgumentException e){
//            System.out.println("seccuess");
//        }
    }


}
