package com.spring.app.github;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("gh-action")
public class GithubActionTurnOffServer implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
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
