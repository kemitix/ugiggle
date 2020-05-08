package net.kemitix.ugiggle.service;

public interface EmailService {
    void send(String recipient, Attachment attachment);
}
