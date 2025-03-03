package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {
    private final LogTrace trace;

    public void save(TraceStatus status, String itemId){
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
