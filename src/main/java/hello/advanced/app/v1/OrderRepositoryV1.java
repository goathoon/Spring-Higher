package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {
    private final HelloTraceV1 trace;

    public void save(String itemId){
        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");

            if (itemId.equals("ex")) {
                throw new IllegalStateException("저장 예외 발생!");
            }

            sleep(1000);

            trace.end(status);
        } catch (Exception e){
            trace.exception(status, e);
            throw e; //  흐름을 바꾸면 안됨. 따라서 예외를 꼭 던질것
        }

    }

    private void sleep(long millis) {
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
