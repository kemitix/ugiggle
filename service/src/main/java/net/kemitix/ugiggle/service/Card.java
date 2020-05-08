package net.kemitix.ugiggle.service;

import java.util.stream.Stream;

public interface Card {
    Stream<Attachment> findAttachments();

    String getName();

    boolean isNotTarget();
}
