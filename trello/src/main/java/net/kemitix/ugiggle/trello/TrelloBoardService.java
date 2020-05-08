package net.kemitix.ugiggle.trello;

import net.kemitix.ugiggle.service.Board;
import net.kemitix.ugiggle.service.BoardService;

import javax.enterprise.context.ApplicationScoped;
import java.util.stream.Stream;

@ApplicationScoped
public class TrelloBoardService implements BoardService {
    @Override
    public Stream<Board> findBoard(String name) {
        return Stream.empty();
    }
}
