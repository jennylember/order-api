package ru.lember.orderapi.data;

import io.micrometer.core.instrument.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class SqlScriptRunnerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ResourceLoader resourceLoader;

    public void executeScript(String sqlScript) {
        jdbcTemplate.execute(sqlScript);
        log.info("SQL script executed successfully");
    }

    public void executeScriptFromResource(String resourcePath) {
        Resource resource = resourceLoader.getResource("classpath:" + resourcePath);
        String sqlScript = null;
        try {
            sqlScript = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("Can't find SQL script classpath: " + resourcePath, e);
        }
        executeScript(sqlScript);
    }
}
