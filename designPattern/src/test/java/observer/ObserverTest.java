package observer;

import org.designpattern.observer.Lion;
import org.designpattern.observer.Muzi;
import org.designpattern.observer.Observer;
import org.designpattern.observer.Subscriber;
import org.junit.jupiter.api.Test;

public class ObserverTest {
    // Observer가 제대로 작동하는지 확인.
    @Test
    void test1() {
        Subscriber subscriber = new Subscriber();
        Observer lion = new Lion();
        Observer muzi = new Muzi();


        subscriber.doSubscribe(lion);
        subscriber.doSubscribe(muzi);
        subscriber.makeNewContents();
        subscriber.alarmToAllObservers();


        subscriber.exitSubscribe(muzi);
        subscriber.alarmToAllObservers();
    }

}
