package syn;

import java.util.ArrayList;
import java.util.List;

// 线程不安全集合
public class UnsafeDemo02 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                // 同步块锁住了共同资源
                synchronized (list){
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // size 的大小不是 10000
        // 因为有多个线程同时操作了 list 的同一位置，因此会有被覆盖的
        System.out.println(list.size());
    }
}
