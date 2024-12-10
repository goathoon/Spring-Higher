package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {
    private final LogTrace trace;

    protected AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    /**
     * public String request(String itemId){
     *
     *         TraceStatus status = null;
     *
     *         try {
     *             status = trace.begin("OrderController.request()");
     *
     *             orderService.orderItem(itemId);
     *
     *             trace.end(status);
     *             return "ok";
     *         } catch (Exception e){
     *             trace.exception(status, e);
     *             throw e; //  흐름을 바꾸면 안됨. 따라서 예외를 꼭 던질것
     *         }
     *     }
     * 해당 템플릿이랑 동일한 거임.
     */
    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            // 로직 호출
            T result = call();

            trace.end(status);
            return result;
        } catch(Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}
