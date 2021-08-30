package com.di.step11;

import org.springframework.stereotype.Component;

@Component("monoPrinter")
public class MonoPrinter implements Printer {

    @Override
    public void print(String msg) {
        System.out.println("MonoPrinter" + msg);
    }
}
