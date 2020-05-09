package net.kemitix.ugiggle.amazon.ses;

import net.kemitix.ugiggle.service.Attachment;
import net.kemitix.ugiggle.service.EmailService;
import net.kemitix.ugiggle.service.UGiggleConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AmazonSimpleEmailService implements EmailService {

    private UGiggleConfig config;

    public AmazonSimpleEmailService() {
    }

    @Inject
    public AmazonSimpleEmailService(UGiggleConfig config) {
        this.config = config;
    }

    @Override
    public void send(Attachment attachment) {
        String recipient = config.getRecipient();
    }
}
