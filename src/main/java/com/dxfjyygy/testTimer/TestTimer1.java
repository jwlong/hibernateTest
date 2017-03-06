package com.dxfjyygy.testTimer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by longjinwen on 2017/2/24.
 */
public class TestTimer1  {
    Timer timer;
    public TestTimer1(int  time){
        timer = new Timer();
        timer.schedule(new TimerTaskTest01(),time*1000);
        //timer.schedule(new TimerTaskTest02(1),time*1000);
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Thread1());
        t1.start();
        new TestTimer1(3);
    }

}


