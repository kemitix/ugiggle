package net.kemitix.ugiggle.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.function.Consumer;

@ApplicationScoped
public class SendReadingListService {

    private BoardService boardService;
    private EmailService emailService;

    public SendReadingListService() {
    }

    @Inject
    public SendReadingListService(
            BoardService boardService,
            EmailService emailService
    ) {
        this.boardService = boardService;
        this.emailService = emailService;
    }

    public void run(String[] args) {
        boardService.findBoard()
                .flatMap(Board::findList)
                .flatMap(CardList::stream)
                .takeWhile(Card::isNotTarget)
                .flatMap(Card::findAttachments)
                .forEach(emailService::send);
    }

}
