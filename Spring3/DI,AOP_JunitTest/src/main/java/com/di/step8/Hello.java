package com.di.step8;

import com.di.step1.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

public class Hello {

//    @Qualifier("monoPrinter")
//    @Resource(name="colorPrinter")
    @Autowired(required=false)
    private Printer printer;

    public Hello(){
        System.out.println("default 생성자");
    }

    // @Autowired
    public Hello(Printer printer) {
        System.out.println("생성자 인젝션");
        this.printer = printer;
    }


    public Printer getPrinter() {
        return printer;
    }


    // @Autowired
    // <property name="printer" ref="printer" />
    public void setPrinter(Printer printer) {
        System.out.println("Setter Method Printer Inject");
        this.printer = printer;
    }




    // @Autowired
    // <property name="printer" ref="printer" />
    public void xxxMalPrinter(Printer printer){
        System.out.println("특정 메소드 인젝션");
        this.printer = printer;
    }




    public void print(String s){
        printer.print(s);
    }



    @Override
    public String toString() {
        return "Hello{" +
                "printer=" + printer +
                '}';
    }
}
