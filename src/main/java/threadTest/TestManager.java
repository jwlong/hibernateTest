package threadTest;

/**
 * Created by longjinwen on 2017/3/6.
 */
public class TestManager {
    public static void main(String[] args) {
        Test01 t01 = new Test01(); // 这个是两个线程同时访问Test01这个对象的synchronized(this)同步代码块时，
        //一个时间只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码才能执行该代码块。
        Thread  th1  = new Thread(t01,"A");
        Thread  th2 = new Thread(t01,"B");
        th1.start();
        th2.start();
    }
}
