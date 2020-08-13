package net.kemitix.ugiggle.service;

import net.kemitix.ugiggle.trello.Attachment;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {
    void send(Attachment attachment) throws MessagingException, IOException;
}
