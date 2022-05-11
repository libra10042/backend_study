package com.di.step5;

import com.di.step1.Printer;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hello {

    private Printer printer;
    private List<String> names;
    private List<Person> persons;
    private Map<String, Integer> ages;
    private Set<String> emails;



    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Map<String, Integer> getAges() {
        return ages;
    }

    public void setAges(Map<String, Integer> ages) {
        this.ages = ages;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public void setEmails(Set<String> emails) {
        this.emails = emails;
    }

    public void print(String s){
        printer.print(s);
    }
}
