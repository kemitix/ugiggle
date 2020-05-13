package net.kemitix.ugiggle.trello;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.TList;
import net.kemitix.ugiggle.AttachmentDirectory;
import net.kemitix.ugiggle.service.Submission;
import net.kemitix.ugiggle.service.ReadingListService;
import net.kemitix.ugiggle.service.UGiggleConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;
import java.util.stream.Stream;

@ApplicationScoped
public class TrelloSubmissionService implements ReadingListService {

    private static final Logger LOG =
            Logger.getLogger(
                    TrelloSubmissionService.class.getName());

    @Inject
    UGiggleConfig config;
    @Inject
    Trello trello;
    @Inject
    AttachmentDirectory attachmentDir;

    @Override
    public Stream<? extends Submission> getReadingList() {
        String board = config.getSlushBoard();
        String list = config.getSlushList();
        return findBoard(board)
                .flatMap(b -> findLists(b, list))
                .flatMap(this::findCards)
                .takeWhile(this::isNotTarget)
                .map(card ->
                        TrelloCard.create(card, trello, attachmentDir));
    }

    private Stream<Board> findBoard(String name) {
        LOG.info("Find board: "+ name);
        return trello.getMemberBoards("me").stream()
                .filter(board -> name.equals(board.getName()))
                .peek(board -> LOG.info("Board: " + board.getName()));
    }

    private Stream<TList> findLists(Board board, String name) {
        LOG.info("Find list: " + name);
        return trello.getBoardLists(board.getId()).stream()
                .filter(tList -> name.equals(tList.getName()))
                .peek(list -> LOG.info("List: " + list.getName()));
    }

    private Stream<Card> findCards(TList tList) {
        LOG.info("Find cards");
        return trello.getListCards(tList.getId()).stream()
                .peek(card -> LOG.info("Card: " + card.getName()));
    }

    private boolean isNotTarget(Card card) {
        return !config.getTargetName().equals(card.getName());
    }
}
