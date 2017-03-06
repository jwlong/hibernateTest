package com.dxfjyygy.testTimer;

import com.dxfjyygy.interfaceTest.TestInterface;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by longjinwen on 2017/2/24.
 */
public class TimerTest2 implements TestInterface{
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int i;
    public static void main(String[] args) {
        Timer timer = new Timer();
        Date date = getTime();
        System.out.println(shareNum);
        timer.schedule(new TimerTask03(),date,2*1000);
        timer.schedule(new TimerTask04(),date,3*1000);
    }
    private static Date getTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,16);
        calendar.set(Calendar.MINUTE,42);
        calendar.set(Calendar.SECOND,50);
        Date time = calendar.getTime();
        return  time;
    }

    public boolean setname() {
        return false;
    }
}


class  TimerTask03 extends TimerTask{
    public void run() {
        System.out.println("TimerTask03 Time up!!!");
    }
}
class  TimerTask04 extends TimerTask{
    int count ;
    public void TimerTask04(int i){
        this.count = i;
    }
    public void run() {
       // new TimerTest2().shareNum ++;
        count --;
        System.out.println("TimerTask04 Time up2!!!");
    }
}