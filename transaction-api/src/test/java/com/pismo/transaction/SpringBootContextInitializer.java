package com.pismo.transaction;

import com.pismo.transaction.config.PostgresSetupConfig;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringBootContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        PostgresSetupConfig.initContainer();
    }
}
