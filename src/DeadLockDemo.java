public class DeadLockDemo {
    public static void main(String[] args) {
        Girl girl1 = new Girl(0, "白雪公主");
        Girl girl2 = new Girl(1, "灰姑娘");

        // 运行到一半就会卡住
        girl1.start();
        girl2.start();
    }
}

// 口红
class Lipstick{}

// 镜子
class Mirror{}

class Girl extends Thread{

    // static 保证资源只有一份
    static Mirror mirror = new Mirror();
    static Lipstick lipstick = new Lipstick();

    int choice;
    String name;

    Girl(int choice, String name){
        this.choice = choice;
        this.name = name;
    }

    @Override
    public void run() {
        if (choice == 0){
            // 获取了 lipstick 的锁，且不将其释放
            synchronized (lipstick){
                System.out.println(name+"获得了 lipstick 的锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 尝试获得第二把锁，但是 mirror 的锁已经被占有
                synchronized (mirror){
                    System.out.println(name+"获得了 mirror 的锁");
                }
            }
//            解决方法：把 synchronized 里的 synchronized 取出
//            synchronized (mirror){
//                System.out.println(name+"获得了 mirror 的锁");
//            }
        }else {
            synchronized (mirror){
                System.out.println(name+"获得了 mirror 的锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lipstick){
                    System.out.println(name+"获得了 lipstick 的锁");
                }
            }
        }
    }
}