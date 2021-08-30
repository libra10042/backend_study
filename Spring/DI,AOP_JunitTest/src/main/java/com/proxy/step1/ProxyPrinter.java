package com.proxy.step1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("proxyPrinter")
public class ProxyPrinter implements Printer {

    @Autowired
    @Qualifier("colorPrinter")
    private Printer printer;

    public ProxyPrinter(ColorPrinter colorPrinter) {
    }


    @Override
    public void Printer(String msg) {
        System.out.println("1. 핵심코드 이전 사전작업");
        System.out.println("2. 핵심코드 실행 : " +  msg);
        System.out.println("3. 핵심코드 이후 사전작업 ");
    }


    @Override
    public String toString() {
        return "ProxyPrinter{" +
                "printer=" + printer +
                '}';
    }
}
