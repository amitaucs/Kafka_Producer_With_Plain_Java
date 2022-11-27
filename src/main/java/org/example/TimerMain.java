package org.example;

import java.util.Timer;

public class TimerMain {

    public static void main(String[] args){

        Timer t = new Timer();
        var producer = new OrderProducer();
        t.scheduleAtFixedRate(producer, 0, 10000);
    }
}
