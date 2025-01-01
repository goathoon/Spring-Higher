package hello.proxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({AppV1Config.class,AppV2Config.class})
@SpringBootApplication(scanBasePackages = "hello.proxy.app.v3") //v3부터는 컴포넌트 스캔대상으로 등록 (@RestController가 컴포넌트 스캔 대상이 되므로 v1,v2는 제외시키기 위함)
// 추가로 Config 패키지를 import하지 않으려고 app쪽 패키지만 scan대상으로 지정
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}
