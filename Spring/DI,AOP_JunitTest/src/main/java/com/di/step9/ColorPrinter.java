package com.di.step9;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component("colorPrinter")
public class ColorPrinter implements Printer {

    @Override
    public void print(String msg) {
        System.out.println("color print" + msg );
    }
}
