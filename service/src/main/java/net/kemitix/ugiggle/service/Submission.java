package net.kemitix.ugiggle.service;

import java.util.stream.Stream;

public interface Submission {
    Stream<Attachment> findAttachments();

    String getName();
}
