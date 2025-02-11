package hello.advanced.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
public class BasicTest {

    @Test
    void basicConfig() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

        //A는 빈으로 등록한다.
        A beanA = applicationContext.getBean("beanA",A.class);
        beanA.helloA();

        Assertions.assertThatThrownBy(()->applicationContext.getBean(B.class))
                .isExactlyInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Slf4j
    static class BasicConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }
    }
    @Slf4j
    static class A {
        public void helloA() {
            log.info("hello A");
        }
    }

    @Slf4j
    static class B {
        public void helloB() {
            log.info("hello B");
        }
    }
}
