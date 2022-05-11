package com.lecture.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Encoder{

    private IEncoder iEncoder;

    public Encoder(@Qualifier("base74Encoder") IEncoder iEncoder){
        this.iEncoder = iEncoder;
//        this.iEncoder = new Base64Encoder();
//        this.iEncoder = new UrlEncoder();
    }

    public void setiEncoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    public String encode(String message){
        return iEncoder.encode(message);
    }
}
