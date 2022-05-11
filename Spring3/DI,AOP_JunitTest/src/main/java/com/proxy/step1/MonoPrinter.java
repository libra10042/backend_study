package com.proxy.step1;


import org.springframework.stereotype.Component;

@Component("monoPrinter")
public class MonoPrinter implements Printer{

    @Override
    public void Printer(String msg) {
        System.out.println("----- mono printer start -----");
        System.out.println(msg);
        System.out.println("----- mono printer start -----");
    }
}
