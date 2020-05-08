package net.kemitix.ugiggle.trello;

import com.julienvey.trello.NotFoundException;
import com.julienvey.trello.Trello;
import net.kemitix.ugiggle.service.Board;
import net.kemitix.ugiggle.service.BoardService;
import net.kemitix.ugiggle.service.UGiggleConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;
import java.util.stream.Stream;

@ApplicationScoped
class TrelloBoardService implements BoardService {

    private static final Logger LOG =
            Logger.getLogger(
                    TrelloBoardService.class.getName());

    private UGiggleConfig config;
    private Trello trello;

    public TrelloBoardService() {
    }

    @Inject
    public TrelloBoardService(
            UGiggleConfig config,
            Trello trello
    ) {
        this.config = config;
        this.trello = trello;
    }

    @Override
    public Stream<Board> findBoard() {
        String slushBoard = config.getSlushBoard();
        try {
            return Stream.of(
                    TrelloBoard.create(
                            trello.getBoard(slushBoard),
                            trello,
                            config));
        } catch (NotFoundException nfe) {
            LOG.warning(String.format(
                    "Board Not Found: %s",
                    slushBoard));
            return Stream.empty();
        }
    }
}
