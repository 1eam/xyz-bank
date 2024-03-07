package com.example.xyzbank.config;

import com.example.xyzbank.domain.auditing.ApplicationAuditAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ApplicationConfig {

    @Bean
    public AuditorAware<Long> auditorAware(){
        return new ApplicationAuditAware();
    }
}
