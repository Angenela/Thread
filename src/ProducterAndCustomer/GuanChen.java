package ProducterAndCustomer;

// 管程法
public class GuanChen {
    public static void main(String[] args) {
        Pool pool = new Pool();
        new Producer(pool).start();
        new Customer(pool).start();
    }
}

// 生产者
class Producer extends Thread{
    private Pool pool;

    public Producer(Pool pool){
        this.pool = pool;
    }

    @Override
    public void run() {
        // 生产 chicken
        for (int i = 0; i < 50; i++) {
            pool.push(new Chicken(i));
            System.out.println("生产了"+i+"只鸡");
        }
    }
}

// 消费者
class Customer extends Thread{
    private Pool pool;

    public Customer(Pool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        // 消费鸡
        for (int i = 0; i < 50; i++) {
            System.out.println("消费了第"+pool.get().id+"只鸡");
        }
    }
}

// 产品
class Chicken{
    public int id;

    public Chicken(int id) {
        this.id = id;
    }
}

// 缓冲池
class Pool{
    Chicken[] chickens = new Chicken[10];
    int count = 0;

    // 生产者放入从产品，涉及到多个生产者进行生产，进行同步
    public synchronized void push(Chicken chicken){
        // 缓冲池是否满了
        if (count == chickens.length){
            // 生产者进行等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 添加产品
        chickens[count++] = chicken;

        // 通知消费者进行消费
        this.notifyAll();
    }

    // 消费者取出产品, 涉及到多个消费者进行消费，进行同步
    public synchronized Chicken get(){
        // 判断是否有产品
        if (count == 0){
            // 消费者进行等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 进行消费
        count--;
        Chicken chicken = chickens[count];
        chickens[count] = null;

        // 通知生产者进行生产
        this.notifyAll();
        return chicken;
    }
}