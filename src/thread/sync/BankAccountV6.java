package thread.sync;

import java.util.concurrent.locks.Lock;
//import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV6 implements BankAccount{

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV6(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());
        // 잔고가 출금액 보다 적으면, 진행하면 안됨

//        if (!lock.tryLock(500, TimeUnit.MILLISECONDS)){
//            log("[진입 실패] 이미 처리중인 작업이 있습니다.");
//            return false;
//        }

        lock.lock();
        try{
            // ==임계 영역 시작==
            log("[검증 시작] 출금액 : " + amount + ", 잔액: " + balance);
            if (balance < amount) {
                log("[검증 실패]");
                return false;
            }

            log("[검증 완료] 출금액 : " + amount + ", 잔액: " + balance);
            sleep(1000);
            balance = balance - amount;
            log("[출금 완료] 출금액 : " + amount + ", 잔액: " + balance);
            // ==임계 영역 종료==
        } finally {
            lock.unlock();
        }

        log("거래 종료: ");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
