package net.kemitix.ugiggle.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.mail.MessagingException;
import java.io.IOException;

@ApplicationScoped
public class SendReadingListService {

    private ReadingListService readingListService;
    private EmailService emailService;

    public SendReadingListService() {
    }

    @Inject
    public SendReadingListService(
            ReadingListService readingListService,
            EmailService emailService
    ) {
        this.readingListService = readingListService;
        this.emailService = emailService;
    }

    public void run(String[] args) {
        readingListService.getReadingList()
                .flatMap(Submission::findAttachments)
                .forEach(this::sendEmail);
    }

    private void sendEmail(Attachment attachment) {
        try {
            emailService.send(attachment);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

}
