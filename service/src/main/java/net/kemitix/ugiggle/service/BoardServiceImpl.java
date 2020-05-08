package net.kemitix.ugiggle.service;

import javax.enterprise.context.ApplicationScoped;
import java.util.stream.Stream;

@ApplicationScoped
public class BoardServiceImpl implements BoardService {
    @Override
    public Stream<Board> findBoard(String name) {
        return Stream.empty();
    }
}
