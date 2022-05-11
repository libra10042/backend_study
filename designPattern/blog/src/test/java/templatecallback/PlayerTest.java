package templatecallback;

import org.designpattern.templatecallback.Player;
import org.designpattern.templatecallback.RockScissorPaper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerTest {
    private Player player;


    @BeforeEach
    void setUp(){
        this.player = new Player();
    }


    @Test
    @DisplayName("플레이어가 가위를 제대로 내는지 테스트")
    void scissor_test(){

        RockScissorPaper strategy = () -> "Scissor";

        assertThat(player.play(strategy), equals("Scissor"));
    }

    @Test
    @DisplayName("플레이어가 가위를 제대로 내는지 테스트")
    void rock_test(){

        RockScissorPaper strategy = () -> "Rock";
        assertThat(player.play(strategy), equals("Rock"));
    }


    @Test
    @DisplayName("플레이어가 가위를 제대로 내는지 테스트")
    void paper_test(){
        RockScissorPaper strategy = () -> "Paper";

        assertThat(player.play(strategy), equals("Paper"));
    }
}
