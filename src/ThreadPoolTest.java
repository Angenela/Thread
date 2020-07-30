import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    public static void main(String[] args) {
        // 创建线程池
        // newFixedThreadPool(线程池的大小)
        ExecutorService service = Executors.newFixedThreadPool(5);

        // 执行
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());

        // 关闭线程池
        service.shutdown();
    }
}

class MyThread implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+"---"+i);
        }
    }
}