package net.kemitix.ugiggle.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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
                .forEach(emailService::send);
    }

}
