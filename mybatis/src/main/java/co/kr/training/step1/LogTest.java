package co.kr.training.step1;


//import org.mybatis.logging.Logger;
//import org.mybatis.logging.LoggerFactory;

// import org.slf4j.Logger
// import org.slf4j.LoogerFactory


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

    static Logger logger = LoggerFactory.getLogger(LogTest.class);

        public static void main (String[] args){
            logger.info("Hello world");

            int result = sum(2, 2);
            System.err.println("2 + 2 = " + result);

        }

        static int sum(int a, int b){
            return a + b;
    }

}
