package net.kemitix.ugiggle.trello;

import com.julienvey.trello.NotFoundException;
import com.julienvey.trello.Trello;
import net.kemitix.ugiggle.service.Board;
import net.kemitix.ugiggle.service.CardList;
import net.kemitix.ugiggle.service.UGiggleConfig;

import java.util.logging.Logger;
import java.util.stream.Stream;

class TrelloBoard implements Board {

    private static final Logger LOG =
            Logger.getLogger(
                    TrelloBoard.class.getName());

    private final com.julienvey.trello.domain.Board board;
    private final Trello trello;
    private final UGiggleConfig config;

    private TrelloBoard(
            com.julienvey.trello.domain.Board board,
            Trello trello,
            UGiggleConfig config
    ) {
        this.board = board;
        this.trello = trello;
        this.config = config;
    }

    public static Board create(
            com.julienvey.trello.domain.Board board,
            Trello trello,
            UGiggleConfig config
    ) {
        return new TrelloBoard(board, trello, config);
    }

    @Override
    public Stream<CardList> findList() {
        String slushList = config.getSlushList();
        try {
            return board.getLists().stream()
                    .filter(tList -> tList.getName().equals(slushList))
                    .findFirst().stream()
                    .map(tList -> TrelloCardList.create(tList, trello, config));
        } catch (NotFoundException nfe) {
            LOG.warning(String.format(
                    "Board Not Found: %s",
                    slushList));
            return Stream.empty();
        }
    }
}
