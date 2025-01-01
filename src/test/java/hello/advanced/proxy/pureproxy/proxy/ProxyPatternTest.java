package hello.advanced.proxy.pureproxy.proxy;

import hello.advanced.proxy.pureproxy.proxy.code.CacheProxy;
import hello.advanced.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.advanced.proxy.pureproxy.proxy.code.RealSubject;
import hello.advanced.proxy.pureproxy.proxy.code.Subject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {
    @Test
    void noProxyTest(){
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient proxyPatternClient = new ProxyPatternClient(realSubject);
        proxyPatternClient.execute();
        proxyPatternClient.execute();
        proxyPatternClient.execute();
        //한번 조회하면 변하지 않는 데이터라면 ..? -> 프록시패턴 (접근제어) 디자인 패턴 사용해볼 법 함
    }

    @Test
    void cacheProxyTest(){
        Subject target = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(target);
        ProxyPatternClient proxyPatternClient = new ProxyPatternClient(cacheProxy);
        proxyPatternClient.execute();
        proxyPatternClient.execute();
        proxyPatternClient.execute();

    }

}
