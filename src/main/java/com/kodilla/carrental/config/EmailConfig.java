package com.kodilla.carrental.config;

import com.kodilla.carrental.scheduler.EmailScheduler;
import com.kodilla.carrental.service.EmailSenderService;
import com.kodilla.carrental.strategy.EmailBodyService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Bean
    public EmailScheduler statisticsEmailScheduler(EmailSenderService emailSenderService,
                                                   AdminConfig adminConfig,
                                                   @Qualifier("statisticsEmailBodyService") EmailBodyService emailBodyService) {
        return new EmailScheduler(emailSenderService, adminConfig, emailBodyService);
    }

    @Bean
    public EmailScheduler reminderEmailScheduler(EmailSenderService emailSenderService,
                                                       AdminConfig adminConfig,
                                                       @Qualifier("reminderEmailBodyService") EmailBodyService emailBodyService) {
        return new EmailScheduler(emailSenderService, adminConfig, emailBodyService);
    }
}
