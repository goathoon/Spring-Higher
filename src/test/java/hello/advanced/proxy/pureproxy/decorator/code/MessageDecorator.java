package hello.advanced.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component{
    private Component component;

    public MessageDecorator(Component component){
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("MessageDeocrator 실행");
        String result = component.operation();
        String decoratedResult = "*****"+ result + "*****";
        log.info("MessageDecorator 꾸미기 적용전 = {}, 적용후 = {}", result, decoratedResult);
        return decoratedResult;
    }
}
