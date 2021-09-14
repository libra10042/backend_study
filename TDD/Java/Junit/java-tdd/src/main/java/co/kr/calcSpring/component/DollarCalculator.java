package co.kr.calcSpring.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DollarCalculator implements ICalculator {

    private int price = 1;
    @Autowired
    private MarketApi marketApi;

    @Override
    public void init(){
        this.price = marketApi.connect();
    }

    @Override
    public int sum(int x, int y) {
        x *= price;
        y *= price;

        return x + y;
    }

    @Override
    public int minus(int x, int y) {
        x *= price;
        y *= price;

        return x -y;
    }
}
