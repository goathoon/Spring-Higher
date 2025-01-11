package hello.advanced.proxy.pureproxy.concreteproxy;

import hello.advanced.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import hello.advanced.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import hello.advanced.proxy.pureproxy.concreteproxy.code.TimeProxy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ConcreteProxyTest {

    @Test
    void noProxy(){
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient concreteClient = new ConcreteClient(concreteLogic);
        concreteClient.execute();
    }

    @Test
    void proxy(){
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        ConcreteClient concreteClient = new ConcreteClient(timeProxy);
        concreteClient.execute();
    }
}
