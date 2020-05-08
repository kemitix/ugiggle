package net.kemitix.ugiggle.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

@ApplicationScoped
public class SendReadingListService {

    private UGiggleConfig config;
    private BoardService boardService;
    private EmailService emailService;

    public SendReadingListService() {
    }

    @Inject
    public SendReadingListService(
            UGiggleConfig config,
            BoardService boardService,
            EmailService emailService
    ) {
        this.config = config;
        this.boardService = boardService;
        this.emailService = emailService;
    }

    public void run(String[] args) {
        findBoard()
                .flatMap(findList())
                .flatMap(findCards())
                .takeWhile(notTargetCard())
                .flatMap(getAttachments())
                .forEach(sendAsEmail());
    }

    private Stream<Board> findBoard() {
        String slushBoard = config.getSlushBoard();
        return boardService.findBoard(slushBoard);
    }

    private Function<CardList, Stream<? extends Card>> findCards() {
        return CardList::stream;
    }

    private Function<Board, Stream<? extends CardList>> findList() {
        String slushList = config.getSlushList();
        return board -> board.findList(slushList);
    }

    private Function<Card, Stream<Attachment>> getAttachments() {
        return Card::findAttachments;
    }

    private Consumer<? super Attachment> sendAsEmail() {
        return attachment -> emailService.send(config.getRecipient(), attachment);
    }

    private Predicate<? super Card> notTargetCard() {
        return card -> !config.getTargetName().equals(card.getName());
    }
}
