package hello.advanced.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

    public void save(String itemId){
        if (itemId.equals("ex")) {
            throw new IllegalStateException("저장 예외 발생!");
        }

        sleep(1000);
    }

    private void sleep(long millis) {
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
