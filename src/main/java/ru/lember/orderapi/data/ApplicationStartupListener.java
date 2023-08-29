package ru.lember.orderapi.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.lember.orderapi.data.SqlScriptRunnerService;

@Component
public class ApplicationStartupListener {

    @Autowired
    private SqlScriptRunnerService sqlScriptRunnerService;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationStartup(ContextRefreshedEvent event) {
        sqlScriptRunnerService.executeScriptFromResource("postgres-script.sql");
    }
}
