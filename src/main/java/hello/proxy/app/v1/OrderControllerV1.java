package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.*;

//@RequestMapping// 스프링은 @Controller or @RequestMapping 이 있어야 Controller로 인식
//하지만 스프링 3.0 이후부터는 @RestController가 필요하다

@RestController
public interface OrderControllerV1 {
    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();
}
