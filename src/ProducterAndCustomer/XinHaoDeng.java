package ProducterAndCustomer;

// 信号灯法，用两个小孩玩玩具说明
public class XinHaoDeng {
    public static void main(String[] args) {
        Toy toy = new Toy();
        new Children1(toy).start();
        new Children2(toy).start();
    }
}

// 小孩一
class Children1 extends Thread{
    Toy toy;

    public Children1(Toy toy) {
        this.toy = toy;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            toy.play1();
        }
    }
}

// 小孩2
class Children2 extends Thread{
    Toy toy;

    public Children2(Toy toy) {
        this.toy = toy;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            toy.play2();
        }
    }
}

// 玩具
class Toy{

    // 信号灯
    private boolean flag = true;

    public synchronized void play1(){
        // 小孩2 再玩，进行等待
        if (flag == false){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("小孩1正在玩");
        flag = !flag;
        notifyAll();
    }

    public synchronized void play2(){
        // 小孩1 再玩，进行等待
        if (flag == true){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("小孩2正在玩");
        flag = !flag;
        notifyAll();
    }
}