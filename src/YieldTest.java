public class YieldTest implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"---线程开始");
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+"---线程结束");
    }

    public static void main(String[] args) {
        YieldTest yieldTest1 = new YieldTest();
        YieldTest yieldTest2 = new YieldTest();

        new Thread(yieldTest1,"a").start();
        new Thread(yieldTest2,"b").start();
    }
}