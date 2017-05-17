package threadTest;

/**
 * synchronized的详细使用
 *
 *
 * */


 public class Test01 implements  Runnable{

    public void run() {
        synchronized (this){
            for(int i=0 ;i<5;i++){
                System.out.println(Thread.currentThread().getName()+"synchronized loop "+i);
            }
        }
    }
}
