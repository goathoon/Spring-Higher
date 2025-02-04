package hello.advanced.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.function.Supplier;

@Slf4j
public class LambdaTest {
    @Test
    void reflection0() {
        Hello target = new Hello();
        execute(()->target.callA());
        execute(()->target.callB());

    }

    private void execute(Supplier<String> function){
        log.info("start");
        String result = function.get();
        log.info("result = {}", result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }
        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
