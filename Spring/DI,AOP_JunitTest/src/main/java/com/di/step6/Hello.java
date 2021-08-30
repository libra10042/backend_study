package com.di.step6;

import com.di.step1.Printer;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hello {

   private Printer printer;
   private List<Person> persons;

   public Hello(){}


    public Hello(Printer printer, List<Person> persons) {
        this.printer = printer;
        this.persons = persons;
    }


    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void print(String s){
        printer.print(s);
    }

}
