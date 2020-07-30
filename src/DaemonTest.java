public class DaemonTest {
    public static void main(String[] args) {
        Thread threadGod = new Thread(new God());
        threadGod.setDaemon(true);  // 默认为 false

        Thread threadPeople = new Thread(new People());

        threadGod.start();
        threadPeople.start();
    }
}

class People implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++){
            System.out.println("快乐的活着");
        }
        System.out.println("再见，世界");
    }
}

class God implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("上帝保护着你");
        }
    }
}
