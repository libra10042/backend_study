package com.di.step9;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component("monoPrinter")
public class MonoPrinter implements Printer {

    @Override
    public void print(String msg) {
        System.out.println("MonoPrinter" + msg);
    }
}
