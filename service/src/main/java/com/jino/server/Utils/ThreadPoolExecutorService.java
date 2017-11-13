package com.jino.server.Utils;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

@Service
public class ThreadPoolExecutorService {

    Logger logger = Logger.getLogger(ThreadPoolExecutorService.class.getName());

    private ExecutorService executorService;

    @PostConstruct
    public void initalize() {
        logger.info("Initalizing executor server.");
        executorService = Executors.newFixedThreadPool(10);
    }

    @PreDestroy
    public void preDestroy() {
        List<Runnable> aborted = executorService.shutdownNow();
        logger.info( aborted.size() + " events ever aborted.");
    }


    public Future<?> submit(Runnable runnable) {
        return executorService.submit(runnable);
    }


}
