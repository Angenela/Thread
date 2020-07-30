// 静态代理总结：
// 真实对象和代理对象实现同一接口
// 代理对象要代理真实对象

// 好处：
// 代理对象可以做真实对象很多不能做的事
// 真实对象只需要专注于自己的事
public class StaticProxy {
    public static void main(String[] args) {
        MarryCompany marryCompany = new MarryCompany(new You());
        marryCompany.marry();
    }
}

interface Marry{
    void marry();
}

// 真实对象，要去结婚
class You implements Marry{
    @Override
    public void marry() {
        System.out.println("结婚啦");
    }
}

// 代理对象，要代理真实对象
class MarryCompany implements Marry{
    You target;

    public MarryCompany(You target) {
        this.target = target;
    }

    @Override
    public void marry() {
        before();
        target.marry();
        after();
    }

    private void after() {
        System.out.println("结婚之后，收钱");
    }

    private void before() {
        System.out.println("结婚之前，布置现场");
    }
}
