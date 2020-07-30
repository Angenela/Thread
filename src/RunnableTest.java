public class RunnableTest implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            System.out.println("林明泽"+i);
        }
    }

    public static void main(String[] args) {
        RunnableTest runnableTest = new RunnableTest();

        // new Thread(runnableTest).start();
        // Lambda 的应用
        new Thread(()->{
            for (int i = 0; i < 100; i++){
                System.out.println("林明泽"+i);
            }
        }).start();

        for (int i = 0; i < 100; i++){
            System.out.println("安基尼拉"+i);
        }
    }
}
