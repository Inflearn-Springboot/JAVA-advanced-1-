package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static util.MyLogger.log;

public class BoundedQueueV6_4 implements BoundedQueue{

    private BlockingQueue<String> queue;

    public BoundedQueueV6_4(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        queue.add(data); // IllegalStateException 예외 발생
    }

    @Override
    public String take() {
        return queue.remove();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
