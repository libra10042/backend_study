package factorymethod;

import org.designpattern.factorymethod.CarFactory;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarFactoryTest {

    //현대차를 제대로 생상하는지 테스트
    @Test
    void test1(){
        assertThat(CarFactory.create("Hyundai").getCarName(), equals("Hyundai"));
    }

    //기아차를 젣로 생산하는지 테스트
    @Test
    void test2(){
        assertThat(CarFactory.create("Kia").getCarName(), equals("Kia"));
    }

    // 차가 없을 때 예외 인지 테스트
    @Test
    void test3(){
        assertThrows(IllegalArgumentException.class, () -> {
               CarFactory.create("not car");
        });
    }

}
