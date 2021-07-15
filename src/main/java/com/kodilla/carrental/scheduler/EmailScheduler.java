package com.kodilla.carrental.scheduler;

import com.kodilla.carrental.config.AdminConfig;
import com.kodilla.carrental.domain.Mail;
import com.kodilla.carrental.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
public class EmailScheduler {
    private static final String SUBJECT = "Car rental: Your daily email!";

    private final EmailSenderService emailSenderService;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendDailyEmail() {
        emailSenderService.sendMail(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                null));//todo
    }
}
