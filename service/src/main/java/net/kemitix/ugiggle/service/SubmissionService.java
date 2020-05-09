package net.kemitix.ugiggle.service;

import java.util.stream.Stream;

public interface SubmissionService {
    Stream<? extends Submission> getReadingList();
}
