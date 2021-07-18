package com.kodilla.carrental.scheduler;

import com.kodilla.carrental.config.AdminConfig;
import com.kodilla.carrental.strategy.EmailBodyService;
import com.kodilla.carrental.service.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailSenderSchedulerTestSuite {

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private EmailSenderService emailSenderService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private EmailBodyService emailBodyService;

    @Test
    public void shouldSendEmail() {
        //Given
        when(adminConfig.getAdminMail()).thenReturn("bartosz.kedziora89@gmail.com");
        when(emailBodyService.emailBodyCreate()).thenReturn("message");
        doNothing().when(emailSenderService).sendMail(any());

        //When
        emailScheduler.sendDailyEmail();

        //Then
        verify(emailSenderService, times(1)).sendMail(any());
    }
}
