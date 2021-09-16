package com.lecture.di;

import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        String url = "www.naver.com/books/it?page=10&name=spring-boot";

        Encoder encoder = new Encoder(new UrlEncoder());

//        Encoder encoder = new Encoder(new Base64Encoder());
        String result = encoder.encode(url);


        System.out.println(result);
    }
}
