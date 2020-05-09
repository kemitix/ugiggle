package net.kemitix.ugiggle.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SendReadingListService {

    private SubmissionService submissionService;
    private EmailService emailService;

    public SendReadingListService() {
    }

    @Inject
    public SendReadingListService(
            SubmissionService submissionService,
            EmailService emailService
    ) {
        this.submissionService = submissionService;
        this.emailService = emailService;
    }

    public void run(String[] args) {
        submissionService.getReadingList()
                .flatMap(Submission::findAttachments)
                .forEach(emailService::send);
    }

}
