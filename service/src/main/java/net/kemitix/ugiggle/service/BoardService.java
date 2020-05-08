package net.kemitix.ugiggle.service;

import java.util.stream.Stream;

public interface BoardService {
    Stream<Board> findBoard();

}
