package com.di.step9;

import org.springframework.stereotype.Component;

@Component("printer")
public interface Printer {
    void print(String msg);
}
