package co.kr.calcSpring.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//@Import({MarketApi.class, DollarCalculator.class})
class DollarCalculatorTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private Calculator calculator;
//    private DollarCalculator dollarCalculator;


    @Test
    public void dollarCalculatorTest(){
        Mockito.when(marketApi.connect()).thenReturn(3000);
//        dollarCalculator.init();

        System.out.println(calculator);
        int sum = calculator.sum(15, 15);
        int minus = calculator.minus(20, 10);
        Assertions.assertEquals(6000, sum);
        Assertions.assertEquals(3000, minus);
    }
}