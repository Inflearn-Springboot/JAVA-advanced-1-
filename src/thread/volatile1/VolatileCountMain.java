package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileCountMain {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread t = new Thread(task, "work");
        log("runFlag = " + task.runFlag);
        t.start();

        sleep(1000);

        task.runFlag = false;
        log("flag = " + task.runFlag + ", count = " + task.count + " main()");

        log("main 종료");
    }

    static class MyTask implements Runnable {
//        boolean runFlag = true;
//        long count;

        volatile boolean runFlag = true;
        volatile long count;

        @Override
        public void run(){
            log("task 시작");
            while(runFlag){
                count++;
                if(count % 100_000_000 == 0){
                    log("flag = " + runFlag + ", count = " + count + " in while()");
                }
            }
            log("flag = " + runFlag + ", count = " + count + " 종료");

        }
    }
}
