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

    private final JdbcTemplate jdbcTemplate;
    private final ResourceLoader resourceLoader;

    @Autowired
    public SqlScriptRunnerService(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.resourceLoader = resourceLoader;
    }

    public void executeScript() {
        Resource resource = resourceLoader.getResource("classpath:" + "postgres-script.sql");
        String sqlScript = null;
        try {
            sqlScript = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
            jdbcTemplate.execute(sqlScript);
            log.info("SQL script executed successfully");
        } catch (IOException e) {
            log.error("Can't find SQL script classpath: postgres-script.sql", e);
        }
    }
}
