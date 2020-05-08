package net.kemitix.ugiggle.trello;

import com.julienvey.trello.Trello;
import com.julienvey.trello.impl.TrelloImpl;
import net.kemitix.ugiggle.service.Attachment;
import net.kemitix.ugiggle.service.Card;
import net.kemitix.ugiggle.service.UGiggleConfig;

import java.util.stream.Stream;

class TrelloCard implements Card {
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

    public static Card create(
            com.julienvey.trello.domain.Card tcard,
            Trello trello,
            UGiggleConfig config
    ) {
        return new TrelloCard(tcard, trello, config);
    }

    @Override
    public Stream<Attachment> findAttachments() {
        return
        trello.getCardAttachments(tcard.getId()).stream()
                .map(attachment -> TrelloAttachment.create(attachment));
    }

    @Override
    public String getName() {
        return tcard.getName();
    }

    @Override
    public boolean isNotTarget() {
        return !config.getTargetName().equals(getName());
    }
}
