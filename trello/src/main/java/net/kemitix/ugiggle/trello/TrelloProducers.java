package net.kemitix.ugiggle.trello;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.JDKTrelloHttpClient;
import net.kemitix.ugiggle.service.UGiggleConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

class TrelloProducers {

    @Produces
    @ApplicationScoped
    TrelloHttpClient trelloHttpClient() {
        return new JDKTrelloHttpClient();
    }

    @Produces
    @ApplicationScoped
    Trello trello(
            UGiggleConfig config,
            TrelloHttpClient httpClient
    ) {
        return new TrelloImpl(
                config.getTrelloKey(),
                config.getTrelloAccessToken(),
                httpClient);
    }

}
