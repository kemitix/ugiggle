package net.kemitix.ugiggle.service;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {
    void send(Attachment attachment) throws MessagingException, IOException;
}
