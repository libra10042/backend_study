package composite;

import org.designpattern.composite.Cafe;
import org.designpattern.composite.Chair;
import org.designpattern.composite.CoffeeBean;
import org.designpattern.composite.Desk;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class CafeTest {
    private Cafe cafe;


    @BeforeEach
    void setUp(){
        cafe = new Cafe();
    }

    // 카페에 있는 물건들의 총합이 올바른지 테스트
    @Test
    void test1(){
        Chair chair = new Chair("No1 Chair" ,3000);
        Desk desk = new Desk("No1. desk", 2000);
        CoffeeBean coffeBean = new CoffeeBean("Columbia", 300);

        cafe.addComposite(chair);
        cafe.addComposite(desk);
        cafe.addComposite(coffeBean);

        assertThat(cafe.getCafeCompositeName(), equals("No1.chair\nNo1.desk\nColumbia"));

    }

    @AfterEach
    void tearDown(){
        cafe.clearAllComposite();
    }


}
