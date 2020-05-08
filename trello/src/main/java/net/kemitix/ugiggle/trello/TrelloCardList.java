package net.kemitix.ugiggle.trello;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.TList;
import net.kemitix.ugiggle.service.Card;
import net.kemitix.ugiggle.service.CardList;
import net.kemitix.ugiggle.service.UGiggleConfig;

import java.util.stream.Stream;

class TrelloCardList implements CardList {
    private final TList tList;
    private final Trello trello;
    private final UGiggleConfig config;

    private TrelloCardList(
            TList tList,
            Trello trello,
            UGiggleConfig config
    ) {
        this.tList = tList;
        this.trello = trello;
        this.config = config;
    }

    public static CardList create(
            TList tList,
            Trello trello,
            UGiggleConfig config
    ) {
        return new TrelloCardList(tList, trello, config);
    }

    @Override
    public Stream<Card> stream() {
        return tList.getCards().stream()
                .map(tcard -> TrelloCard.create(tcard, trello, config));
    }
}
