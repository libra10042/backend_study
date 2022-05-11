package com.di.step10;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.annotation.Resources;

@Component("hello")
public class Hello {

    @Autowired
    @Qualifier("colorPrinter")
    private Printer printer;

    @Value("#{'SPRING' + ' EL '}")
    String name;

    public Hello(ColorPrinter colorPrinter) {
    }


    public void print(String s){
        printer.print(s);
    }


    public void print2(String s){
        if(name == null){
            printer.print(s);
        }else {
            printer.print(name + " " + s);
        }
    }
}
