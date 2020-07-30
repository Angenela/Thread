package syn;

// 同步不安全案例，买票
public class UnsafeDemo01 implements Runnable {

    // 票数
    private Integer tickets = 10;

    // 标识符
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            buy();
        }
    }

    private synchronized void buy() {
        if (tickets >= 0) {
            System.out.println(Thread.currentThread().getName() + "---" + tickets--);
            try {
                // 放大并发问题
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            flag = false;
        }
    }


    public static void main(String[] args) {
        UnsafeDemo01 unsafeDemo01 = new UnsafeDemo01();

        new Thread(unsafeDemo01, "lmz").start();
        new Thread(unsafeDemo01, "angenela").start();
        new Thread(unsafeDemo01, "tsy").start();
    }
}


