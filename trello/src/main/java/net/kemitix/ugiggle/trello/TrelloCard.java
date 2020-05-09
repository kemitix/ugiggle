package net.kemitix.ugiggle.trello;

import com.julienvey.trello.Trello;
import net.kemitix.ugiggle.service.Attachment;
import net.kemitix.ugiggle.service.Submission;
import net.kemitix.ugiggle.service.UGiggleConfig;

import java.util.stream.Stream;

class TrelloCard implements Submission {
    private final com.julienvey.trello.domain.Card tcard;
    private final Trello trello;
    private final UGiggleConfig config;

    private TrelloCard(
            com.julienvey.trello.domain.Card tcard,
            Trello trello,
            UGiggleConfig config
    ) {
        this.tcard = tcard;
        this.trello = trello;
        this.config = config;
    }

    public static TrelloCard create(
            com.julienvey.trello.domain.Card tcard,
            Trello trello,
            UGiggleConfig config
    ) {
        return new TrelloCard(tcard, trello, config);
    }

    public Stream<Attachment> findAttachments() {
        return trello.getCardAttachments(tcard.getId()).stream()
                .map(attachment -> TrelloAttachment.create(attachment, tcard));
    }

    public String getName() {
        return tcard.getName();
    }
}
