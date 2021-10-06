package adapter;

import org.designpattern.adapter.*;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class CarAdapterTest {
    // 장난감 현대차가 제대로 동작하는지 확인.
    @Test
    void test1(){
        Car car = new HyundaiCar();
        ToyCar toyCar = new CarAdapter(car);

        assertThat(toyCar.moveActionToCar(), equals("wongwong"));
    }

    // 장난감 기아차가 제대로 동작하는지 확인
    @Test
    void test2(){
        Car car = new KiaCar();
        ToyCar toyCar = new CarAdapter(car);

        assertThat(toyCar.moveActionToCar(), equals("wingwing"));

    }

}
