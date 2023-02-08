package ir.aniranmp.web.config;


import com.fasterxml.jackson.databind.ObjectMapper;


import ir.aniranmp.web.util.BeautyLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Configuration
public class ApplicationLifecycle {

    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ObjectMapper objectMapper;

    private static boolean slaveDbChangeFlag = false;


    private static final Logger log = LoggerFactory.getLogger(ApplicationLifecycle.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @PostConstruct
    private void onStart() {
        BeautyLog.println();
    }

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

}
