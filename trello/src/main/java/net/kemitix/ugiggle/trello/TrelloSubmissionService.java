package net.kemitix.ugiggle.trello;

import com.julienvey.trello.NotFoundException;
import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.TList;
import net.kemitix.ugiggle.service.Submission;
import net.kemitix.ugiggle.service.SubmissionService;
import net.kemitix.ugiggle.service.UGiggleConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;
import java.util.stream.Stream;

@ApplicationScoped
class TrelloSubmissionService implements SubmissionService {

    private static final Logger LOG =
            Logger.getLogger(
                    TrelloSubmissionService.class.getName());

    private UGiggleConfig config;
    private Trello trello;

    public TrelloSubmissionService() {
    }

    @Inject
    public TrelloSubmissionService(
            UGiggleConfig config,
            Trello trello
    ) {
        this.config = config;
        this.trello = trello;
    }

    @Override
    public Stream<? extends Submission> getReadingList() {
        String board = config.getSlushBoard();
        String list = config.getSlushList();
        return findBoard(board)
                .flatMap(b -> findLists(b, list))
                .flatMap(this::findCards)
                .takeWhile(this::isNotTarget)
                .map(card -> TrelloCard.create(card,trello, config));
    }

    private boolean isNotTarget(Card card) {
        return !config.getTargetName().equals(card.getName());
    }

    private Stream<Card> findCards(TList tList) {
        return tList.getCards().stream();
    }

    private Stream<TList> findLists(Board board, String name) {
        return board.getLists().stream()
                .filter(tList -> tList.getName().equals(name));
    }

    private Stream<Board> findBoard(String name) {
        try {
            return Stream.of(trello.getBoard(name));
        } catch (NotFoundException nfe) {
            LOG.warning(String.format(
                    "Board Not Found: %s",
                    name));
            return Stream.empty();
        }
    }
}
