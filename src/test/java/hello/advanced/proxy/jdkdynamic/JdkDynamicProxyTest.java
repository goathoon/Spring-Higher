package hello.advanced.proxy.jdkdynamic;

import hello.advanced.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {
    @Test
    void dynamicA() {
        AInterface target = new AImpl();

        // 프록시가 사용해야할 로직이 곧 handler이다
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        //프록시 생성
        //인터페이스가 여러개 있을 수도 있으니까
//        Object proxy = Proxy.newProxyInstance(AInterface.class.getClassLoader(),
//                new Class[]{AInterface.class}, handler);

        //프록시가 곧 A라는 Interface type을 구현해서 만들어지므로 type casting한다.
        //당연히 downcasting은 위험한 짓이지만, 명시적으로 알고있기때문에 가능한 일이다. (예를들어 instanceof로 검사한다든지)
        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(),
                new Class[]{AInterface.class}, handler);

        proxy.call();
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());
    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(),
                new Class[]{BInterface.class}, handler);

        proxy.call();
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());
    }
}
