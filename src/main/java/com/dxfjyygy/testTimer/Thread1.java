package com.dxfjyygy.testTimer;

/**
 * Created by longjinwen on 2017/2/24.
 */
public class Thread1 implements  Runnable {
    public void run() {
        try {
        for(int i = 1; i<=3; i++){

                Thread.sleep(1000);
                System.out.println("read second:"+i);
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
