package templatemethod;

import org.designpattern.templatemethod.Boy;
import org.designpattern.templatemethod.Girl;
import org.designpattern.templatemethod.Person;
import org.junit.jupiter.api.Test;

public class PersonTest {

    // 소년과 소녀가 제대로 나갈준비하는지 알아보자
    @Test
    void test1(){
        Person boy =new Boy();
        Person girl = new Girl();

        boy.readyToTrip();
        System.out.println("---------");
        girl.readyToTrip();
    }
}
