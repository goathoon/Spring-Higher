package hello.advanced.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();
        // 공통 로직 1 시작
        log.info("start");
        String result1 = target.callA();
        log.info("result = {}", result1);
        // 공통 로직1 종료

        // 공통 로직2 시작
        log.info("start");
        String result2 = target.callB();
        log.info("result = {}", result2);
        // 공통 로직2 종료
    }

    @Test
    void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 클래스 정보
        Class classHello = Class.forName("hello.advanced.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        //callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1 = {}", result1);

        //callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2 = {}", result2);

    }

    @Test
    void reflection2() throws Exception {
        // 클래스 정보
        Class classHello = Class.forName("hello.advanced.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        dynamicCall(classHello.getMethod("callA"), target);
        dynamicCall(classHello.getMethod("callB"), target);
    }

    @Test
    // 셀프 추가
    void reflection3() throws Exception {
        // 클래스 정보
        Hello target = new Hello();
        dynamicCall(target.getClass().getMethod("callA"), target);
        dynamicCall(target.getClass().getMethod("callB"), target);
    }

    /**
     * 3. 주요 차이점 요약
     * 항목	reflection2	| reflection3
     * 클래스 정보 획득 방식 :	Class.forName("클래스 이름") |target.getClass()
     * 런타임 클래스 로딩	: 클래스 이름 기반으로 동적 로딩 | 런타임 객체에서 클래스 정보 획득
     * 유지보수성	: 클래스 이름 변경 시 코드 수정 필요 	클래스 이름 변경에 영향 없음
     * 대상 객체 필요 여부	: 대상 객체 없이 클래스 정보 획득 가능	| 대상 객체가 있어야 사용 가능
     * 사용 사례 : 동적 클래스 로딩, 플러그인 시스템  |  일반적인 객체 기반 리플렉션
     * @param method
     * @param target
     * @throws Exception
     */

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
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
