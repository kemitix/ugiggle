package net.kemitix.ugiggle.amazon.ses;

import net.kemitix.ugiggle.service.Attachment;
import net.kemitix.ugiggle.service.EmailService;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AmazonSimpleEmailService implements EmailService {
    @Override
    public void send(String recipient, Attachment attachment) {

    }
}
