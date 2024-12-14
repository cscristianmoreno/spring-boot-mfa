package com.spring.app.github;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.spring.app.utils.ServerUtil;

@Component
@Profile("gh-action")
public class GithubActionTurnOffServer implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ServerUtil.off();
    }
}
