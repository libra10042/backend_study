package abstractfactory;

import org.designpattern.abstractfactory.ShapeFactory;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;

public class ShapeFactoryTest {
    // 도형을 제대로 생산하는지 확인
    @Test
    void test1(){
        assertThat(String.valueOf(ShapeFactory.create("CIRCLE")), equals("Circle"));
        assertThat(String.valueOf(ShapeFactory.create("RECTANGLE")),
                equals("Rectangle"));
    }
}
