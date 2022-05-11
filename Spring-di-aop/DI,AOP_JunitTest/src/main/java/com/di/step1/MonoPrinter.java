package com.di.step1;

public class MonoPrinter implements Printer{

    @Override
    public void print(String msg) {
        System.out.println("MonoPrinter" + msg);
    }
}
