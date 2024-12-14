package com.spring.app.utils;

import java.util.Timer;
import java.util.TimerTask;

public abstract class ServerUtil {
    
    public static void off() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int duration = 5;

            @Override
            public void run() {
                System.out.printf("Server will turn off in %d seconds", duration);
                System.out.println();
                
                if (duration == 0) {
                    System.exit(0);
                }

                duration--;
            }

        }, 3000, 1000);
    }
}
