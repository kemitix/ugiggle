package net.kemitix.ugiggle.service;

import net.kemitix.ugiggle.trello.TrelloCard;

import java.util.stream.Stream;

public interface ReadingListService {
    Stream<TrelloCard> getReadingList();
}
