package decorator;

import org.designpattern.decorator.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class ToppingDecoratorTest {
    @Test
    @DisplayName("치즈피자에 토마토 토핑이 제대로 올라가는지 테스트")
    void FreshTomato_with_ChessePizza(){

        Pizza cheesePizzaWithFreshTomato = new FreshTomato(new CheesePizza());

        assertThat(cheesePizzaWithFreshTomato.getDescription(), equals("CheesePizza with FreshTomato"));
        assertThat(String.valueOf(cheesePizzaWithFreshTomato.getCost()), equals(10_300));
    }

    @Test
    @DisplayName("콤비네이션 피자에 올리브 토피엥 제대로 올라가는지 테스트")
    void Olive_with_CombinationPizza(){

        Pizza combinationPizzaWithOlive = new Olive(new CombinationPizza());

        assertThat(combinationPizzaWithOlive.getDescription(), equals("CombinationPizza with Olive"));
        assertThat(String.valueOf(combinationPizzaWithOlive.getCost()), equals(12_400));
    }

}
