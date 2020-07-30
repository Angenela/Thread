public class GetStateTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
            }
        });

        Thread.State state = thread.getState();
        System.out.println(state);      // NEW

        thread.start();
        state = thread.getState();
        System.out.println(state);     // RUNNABLE
    }
}
