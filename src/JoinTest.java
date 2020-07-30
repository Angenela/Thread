public class JoinTest implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++){
            System.out.println("VIP来了---"+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new JoinTest());
        thread.start();

        for (int i = 0; i < 100; i++){
            if (i == 50){
                thread.join();
            }
            System.out.println("普通客户来了---"+i);
        }
    }
}
