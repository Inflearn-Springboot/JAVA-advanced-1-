package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static util.MyLogger.log;

public class BoundedQueueV6_3 implements BoundedQueue{

    private BlockingQueue<String> queue;

    public BoundedQueueV6_3(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
//        boolean result = queue.offer(data, 1, TimeUnit.NANOSECONDS);
//        log("저장 시도 결과 = " + result);
    }

    @Override
    public String take() {
        return queue.poll();
    }
}
