import java.util.concurrent.*;

public class CallableTest implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName()+"----"+i);
        }
        return 0;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建目标对象
        CallableTest callableTest1= new CallableTest();
        CallableTest callableTest2 = new CallableTest();

        // 创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(2);

        // 提交执行
        Future<Integer> result1 = ser.submit(callableTest1);
        Future<Integer> result2 = ser.submit(callableTest2);

        // 获取结果
        Integer r1 = result1.get();

        // 关闭服务
        ser.shutdownNow();

        FutureTask<Integer> futureTask = new FutureTask<>(callableTest1);
        new Thread(futureTask).start();
    }
}
