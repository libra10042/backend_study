package proxy;

import org.designpattern.proxy.Internet;
import org.designpattern.proxy.ProxyInternet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ProxyInternetTest {
    private Internet internet;

    @BeforeEach
    void setUp(){
        internet = new ProxyInternet();
    }
    @Test
    @DisplayName("도메인이 올바르게 연결되는지 테스트")
    void proxy_internet_conntecting_test(){
        assertThat(internet.connectTo("naver.com"), equals("Connecting to naver.com"));
    }

    @Test
    @DisplayName("접근금지인 도메인은 예외를 던지는지 테스트")
    void proxy_internet_ban_test(){
       /* Then */
        assertThrows(IllegalArgumentException.class, () -> internet.connectTo("abc.com"));
    }
}
