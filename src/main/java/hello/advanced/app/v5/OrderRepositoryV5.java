package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
public class OrderRepositoryV5 {
    private final TraceTemplate traceTemplate;


    public OrderRepositoryV5(LogTrace logTrace) {
        this.traceTemplate = new TraceTemplate(logTrace);
    }

    public void save(String itemId){
        traceTemplate.execute("OrderRepository.save()", ()-> {
            if (itemId.equals("ex")) {
                throw new IllegalStateException("저장 예외 발생!");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(long millis) {
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
