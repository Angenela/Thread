import java.util.concurrent.locks.ReentrantLock;

public class LockTest implements Runnable {
    // 票数
    private Integer tickets = 10;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                // sleep 不能放在 lock 区域内
                // 要不然 while 执行的太快就会一直锁住
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 加锁
            lock.lock();
            try {
                if (tickets > 0){
                    System.out.println(Thread.currentThread().getName() + "--->" + tickets--);

                }else {
                    break;
                }
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();

        new Thread(lockTest, "angenela").start();
        new Thread(lockTest, "lmz").start();
        new Thread(lockTest, "tsy").start();
    }
}