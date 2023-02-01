package com.musala.alvaro.testdrones.configuration.log;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.db.ColumnMapping;
import org.apache.logging.log4j.core.appender.db.jdbc.ColumnConfig;
import org.apache.logging.log4j.core.appender.db.jdbc.ConnectionSource;
import org.apache.logging.log4j.core.appender.db.jdbc.DriverManagerConnectionSource;
import org.apache.logging.log4j.core.appender.db.jdbc.JdbcAppender;
import org.apache.logging.log4j.core.filter.ThresholdFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import javax.annotation.PostConstruct;

@Configuration
public class LogConfiguration {

	private final Environment env;
	
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	
	@Autowired
	public LogConfiguration(Environment env) {
		this.env = env;
		
	}

	@PostConstruct
    public void onStartUp() {

        /*ColumnConfig[] columnConfigs = new ColumnConfig[5];
        columnConfigs[0] = ColumnConfig.newBuilder()
                .setName("logger")
                .setPattern("%logger")
                .setUnicode(false)
                .build();
        columnConfigs[1] = ColumnConfig.newBuilder()
                .setName("level")
                .setPattern("%level")
                .setUnicode(false)
                .build();
        columnConfigs[2] = ColumnConfig.newBuilder()
                .setName("message")
                .setPattern("%message")
                .setUnicode(false)
                .build();
        columnConfigs[3] = ColumnConfig.newBuilder()
                .setName("exception")
                .setPattern("%ex{full}")
                .setUnicode(false)
                .build();
        columnConfigs[4] = ColumnConfig.newBuilder()
                .setName("date")
                .setPattern("%d{dd-MM-yyyy HH:mm:ss.SSS}")
                .setUnicode(false)
                .build();


        ConnectionSource connectionSource = DriverManagerConnectionSource.newBuilder()
                .setDriverClassName("org.h2.Driver")
                .setConnectionString(url)
                .setUserName(username.toCharArray())
                .setPassword(password.toCharArray())
                .build();

        JdbcAppender appender = JdbcAppender.newBuilder()
                .setTableName("logger")
                .setConnectionSource(connectionSource)
                .setName("dblog")
                .setIgnoreExceptions(false)
                .setBufferSize(1)
                .setColumnConfigs(columnConfigs)
                .build();

        appender.start();
        Logger logger = (Logger) LogManager.getLogger("com");
        logger.addAppender(appender);*/
    }
	
}
