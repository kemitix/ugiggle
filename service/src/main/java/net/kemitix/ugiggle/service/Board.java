package net.kemitix.ugiggle.service;

import java.util.stream.Stream;

public interface Board {
    Stream<CardList> findList(String name);
}
