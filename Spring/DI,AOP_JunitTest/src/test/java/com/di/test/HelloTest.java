package com.di.test;

import com.di.step1.ColorPrinter;
import com.di.step1.Hello;
import com.di.step1.MonoPrinter;
import com.di.step1.Printer;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.logging.LoggerFactory;

import java.awt.*;
import java.util.logging.Logger;


class HelloTest {

    private final static Logger LOG = Logger.getGlobal();

    Printer printer1 = new ColorPrinter();
    Printer printer2 = new MonoPrinter();

    @Test
    public void Test(){
    }

    // setter injection
    @Test
    public void test1(){
        Hello hello = new Hello();
        hello.setPrinter(printer1); // setter injection
        LOG.info("Test1");
    }

    // contruction injection
    @Test
    public void test2(){
        Hello hello2 = new Hello(printer2); // contruction
        hello2.setPrinter(printer2);
        LOG.info("Test2");
    }

}