package com.aop.step1.entity;

import com.aop.step1.ui.Printer;

import java.util.ArrayList;
import java.util.HashMap;

public class Person {

    String name;
    String major;
    ArrayList<ExamScore> examList;
    private Printer printer;

    public Person(){

    }


    public Person(String name, String major, ArrayList<ExamScore> examList, Printer printer) {
        this.name = name;
        this.major = major;
        this.examList = examList;
        this.printer = printer;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public ArrayList<ExamScore> getExamList() {
        return examList;
    }

    public void setExamList(ArrayList<ExamScore> examList) {
        this.examList = examList;
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }



    public void print() {
        String str =  "Person{" +
                "name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", examList=" + examList +
                ", printer=" + printer +
                '}';

        printer.print(str);
    }



    public HashMap<String, Float> getTot(){
        ArrayList<ExamScore> exams = this.getExamList();
        HashMap<String, Float> gradeAvg = new HashMap<String, Float>();

        for(ExamScore exam : exams){
            float avg  = (float) (exam.getKor() + exam.getEng()+ exam.getMat()) /3 ;
            gradeAvg.put(exam.getGrade(), avg);

        }

        System.out.println(gradeAvg.toString());
        return gradeAvg;

    }


    public HashMap<String, Float> getTot(String grade){
        ArrayList<ExamScore> exams = this.getExamList();
        HashMap<String, Float> gradeAvg = new HashMap<String, Float>();


        for(ExamScore exam : exams) {
            if(exam.getGrade().equals(grade)){
                Float avg = (float) (exam.getEng() + exam.getKor() + exam.getMat()) /3;
                gradeAvg.put(exam.getGrade(), avg);
            }
        }

        System.out.println(gradeAvg.toString());

        return gradeAvg;
    }

}
