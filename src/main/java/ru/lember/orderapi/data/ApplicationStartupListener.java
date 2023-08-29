package ru.lember.orderapi.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationStartupListener {

    private final SqlScriptRunnerService sqlScriptRunnerService;

    @Autowired
    public ApplicationStartupListener(SqlScriptRunnerService sqlScriptRunnerService) {
        this.sqlScriptRunnerService = sqlScriptRunnerService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationStartup(ContextRefreshedEvent event) {
        log.info("SQL script is starting...");
        sqlScriptRunnerService.executeScript();
    }
}
