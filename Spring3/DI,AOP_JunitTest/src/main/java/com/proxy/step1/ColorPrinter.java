package com.proxy.step1;


import org.springframework.stereotype.Component;

@Component("colorPrinter")
public class ColorPrinter implements Printer{
    @Override
    public void Printer(String msg) {
        System.out.println("======= color printer start ========");
        System.out.println(msg);
        System.out.println("------- color printer start --------");
    }
}
