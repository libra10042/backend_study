package com.di.step1;


import java.util.logging.Logger;

public class Hello {
   private final static Logger LOG = Logger.getGlobal();

   private Printer printer;
   private String name = "";

   public Hello(){

   }

   public Hello(Printer printer, String name){
       LOG.info("step2 test");
       this.printer = printer;
       this.name = name;

   }

    public Hello(Printer printer) {
        LOG.info("step1 test");
        this.printer = printer;
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "printer=" + printer +
                '}';
    }


    public void print(String s) {
       if(this.name != null)
           printer.print(this.name + " " + s);
       else
           printer.print(s);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
