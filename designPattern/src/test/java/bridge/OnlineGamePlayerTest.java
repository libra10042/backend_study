package bridge;

import org.designpattern.bridge.*;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class OnlineGamePlayerTest {
    private GamePlayer gamePlayer;


    // LOL이 올바르게 실행되는지 테스트
    @Test
    void test1(){
        final PlayGameAPI LOL = new LOL();
        final String id = "Ashe";
        final String password = "ad";

        gamePlayer = new OnlineGamePlayer(LOL, id, password);

        assertThat(gamePlayer.play(), equals("LOL Connect complete! [id=Ashe password=ad]"));
    }


    // Overwatch가 올바르게 실행되는지 테스트
    @Test
    void test2(){
        final PlayGameAPI LOL = new Overwatch();

    }

}
