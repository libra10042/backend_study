package com.aop.step1.entity;

public class ExamScore {

    String grade;
    int kor;
    int eng;
    int mat;

    public ExamScore(){

    }


    public ExamScore(String grade, int kor, int eng, int mat) {
        this.grade = grade;
        this.kor = kor;
        this.eng = eng;
        this.mat = mat;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public int getMat() {
        return mat;
    }

    public void setMat(int mat) {
        this.mat = mat;
    }

    @Override
    public String toString() {
        return "ExamScore{" +
                "grade='" + grade + '\'' +
                ", kor=" + kor +
                ", eng=" + eng +
                ", mat=" + mat +
                '}';
    }







}
