package com.example.myapplication;

import static com.example.myapplication.MainActivity.fragment;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    boolean running;
    Timer timer=new Timer();
    public void startWork(int allTime){
        MyTimerTask task=new MyTimerTask(allTime);
        running=true;
        if(timer!=null){

            timer=new Timer();
        }
        timer.scheduleAtFixedRate(task,0,1000);
    }
    public void stopWork(){
        running=false;
        timer.cancel();
    }
    private class MyTimerTask extends TimerTask{

        Integer tempTime;
        public MyTimerTask(int allTime) {
            this.tempTime = allTime;
            running=false;
        }


        @Override
        public void run() {
            if(running){
                tempTime--;
                fragment.getActivity().runOnUiThread(() -> fragment.setupTimerText(tempTime));


                Log.d("time_left", String.valueOf(tempTime));
                if (tempTime==0){
                    stopWork();
                }
            }
        }
    }
}
