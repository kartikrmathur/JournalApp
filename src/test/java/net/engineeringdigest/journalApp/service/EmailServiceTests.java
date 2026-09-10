package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Service.EmailService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Disabled
    @Test
    void testSendMail() {
        emailService.sendEmail("kartiksmathur@gmail.com",
                "Testing Java mail sender",
                "Hi, aap kaise hain ?");
    }
}
