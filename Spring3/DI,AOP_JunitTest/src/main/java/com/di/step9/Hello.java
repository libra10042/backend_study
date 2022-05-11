package com.di.step9;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("hello")
public class Hello {


    @Autowired
    @Qualifier("monoPrinter")
    private Printer printer;

    @Value("#{'SPRING' + ' EL '}")
    String name;

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
