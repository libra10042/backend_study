package com.di.step1;

public class ColorPrinter implements Printer {

    @Override
    public void print(String msg) {
        System.out.println("color print" + msg );
    }
}
