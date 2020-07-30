public class ThreadTest extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            System.out.println("林明泽"+i);
        }
    }

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        threadTest.start();

        for (int i = 0; i < 100; i++){
            System.out.println("安基尼拉"+i);
        }
    }
}
