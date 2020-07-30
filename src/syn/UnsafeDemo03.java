package syn;

// 银行取钱
public class UnsafeDemo03 {
    public static void main(String[] args) {
        Account account = new Account(100);
        new Bank(account, 50).start();
        new Bank(account, 100).start();
    }
}


// 账户
class Account{
    public int money;

    public Account(int money){
        this.money = money;
    }
}

// 银行, 因为不是被多个线程同时操作的对象，所以继承 Thread，当然实现 Runnable 也可以
class Bank extends Thread{

    private  Account account;
    private int getMoney;

    public Bank(Account account, int getMoney){
        this.account = account;
        this.getMoney = getMoney;
    }

    @Override
    public void run() {
        // 同步块锁住了共同资源
        synchronized (account){
            if (account.money >= getMoney){
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                account.money -= getMoney;
                System.out.println("取出了："+getMoney+"，剩余："+account.money);
            }
        }
    }
}
