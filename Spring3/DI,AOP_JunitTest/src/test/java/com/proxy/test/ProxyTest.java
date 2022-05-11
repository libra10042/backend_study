package com.proxy.test;

import com.proxy.step1.ColorPrinter;
import com.proxy.step1.Config;
import com.proxy.step1.Printer;
import com.proxy.step1.ProxyPrinter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class})
public class ProxyTest {


    @Autowired
    ApplicationContext context;


    @Test
    public void test1(){
        Printer printer = new ColorPrinter();
        printer.Printer(new Date().toString());


        Printer printer1 = new ProxyPrinter(new ColorPrinter());

        printer.Printer(new Date().toString());
    }


}


