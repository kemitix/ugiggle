package net.kemitix.ugiggle.trello;

import net.kemitix.ugiggle.service.Board;
import net.kemitix.ugiggle.service.BoardService;
import net.kemitix.ugiggle.service.UGiggleConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.stream.Stream;

@ApplicationScoped
public class TrelloBoardService implements BoardService {

    private UGiggleConfig config;

    public TrelloBoardService() {
    }

    @Inject
    public TrelloBoardService(UGiggleConfig config) {
        this.config = config;
    }

    @Override
    public Stream<Board> findBoard() {
        String slushBoard = config.getSlushBoard();
        return Stream.empty();
    }
}
