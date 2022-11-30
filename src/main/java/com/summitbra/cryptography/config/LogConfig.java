package com.summitbra.cryptography.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class LogConfig {

    private static final Logger logger = LogManager.getLogger(LogConfig.class);
    private static final String SERVICE_LOG_CODE = "servico.log.cod";


    public void setInfoLogging(String message){
        logger.info(message);
    }

    public void error(String loggerMessage, String status, Throwable throwable){
        ThreadContext.clearAll();
        ThreadContext.put("servico.log.cod", status);
        logger.error(loggerMessage, throwable);
    }

    public void errorRequest(String loggerMessage, int status, Throwable throwable){
        ThreadContext.clearAll();
        ThreadContext.put("servico.log.cod", String.valueOf(status));
        logger.error(loggerMessage, throwable);
    }

    public static void warn(HttpStatus status, String loggerMessage) {
        ThreadContext.clearMap();
        ThreadContext.put(SERVICE_LOG_CODE, String.valueOf(status.value()));
        logger.warn(loggerMessage);
    }
}
