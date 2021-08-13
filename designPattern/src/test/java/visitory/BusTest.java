package visitory;

import javafx.beans.binding.BooleanExpression;
import org.designpattern.visitor.Bus;
import org.designpattern.visitor.CarViewVisitor;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class BusTest {
    // 버스가 운전하고 상태를 제대로 출력하는지 테스트()
    @Test
    void test1(){
        /*Given*/
        Bus bus = new Bus(10);
        /*When*/
        bus.drive();
        /*Then*/
        assertThat(bus.getFuel(), equalTo(9));
        assertThat(bus.visit(new CarViewVisitor()), equalTo("현재 버시의 기름 상태:9"));
    }

}
