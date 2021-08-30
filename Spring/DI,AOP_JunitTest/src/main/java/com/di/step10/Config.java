package com.di.step10;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {


    @Bean
    public Hello hello(){
        return new Hello(colorPrinter());
    }

    @Bean
    public ColorPrinter colorPrinter(){
        return new ColorPrinter();

    }


    @Bean
    public MonoPrinter monoPrinter(){
        return new MonoPrinter();
    }


}
