package net.kemitix.ugiggle.service;

import java.util.stream.Stream;

public interface ReadingListService {
    Stream<? extends Submission> getReadingList();
}
