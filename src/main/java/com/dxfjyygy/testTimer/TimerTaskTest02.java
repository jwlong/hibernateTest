package com.dxfjyygy.testTimer;

import java.util.TimerTask;

/**
 * Created by longjinwen on 2017/2/24.
 */
public class TimerTaskTest02 extends TimerTask {


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }


    private int i;


    public TimerTaskTest02(int i){
        i = this.i;
    }

    public void run() {
        // print read second
        System.out.println("second "+i);
    }
}
