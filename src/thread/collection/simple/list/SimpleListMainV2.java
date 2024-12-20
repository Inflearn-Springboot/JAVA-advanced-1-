package thread.collection.simple.list;

import static util.MyLogger.log;

public class SimpleListMainV2 {
    public static void main(String[] args) throws InterruptedException {
//        test(new BasicList());
        BasicList basicList = new BasicList();
        SyncProxyList proxyList = new SyncProxyList(basicList);
        test(proxyList);
//        == test(new SyncProxyList(new BasicList()));
    }


    private static void test(SimpleList list) throws InterruptedException {
        log(list.getClass().getSimpleName());

        Runnable addA = new Runnable() {
            @Override
            public void run() {
                list.add("A");
                log("Thread1 : list.add(A)");
            }
        };
        Runnable addB = new Runnable() {
            @Override
            public void run() {
                list.add("B");
                log("Thread2 : list.add(B)");
            }
        };

        Thread thread1 = new Thread(addA, "Thread-1");
        Thread thread2 = new Thread(addB, "Thread-2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        log(list);
    }
}
