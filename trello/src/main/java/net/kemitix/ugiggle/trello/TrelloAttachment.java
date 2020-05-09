package net.kemitix.ugiggle.trello;

import net.kemitix.ugiggle.service.Attachment;

class TrelloAttachment implements Attachment {
    private final com.julienvey.trello.domain.Attachment attachment;

    private TrelloAttachment(
            com.julienvey.trello.domain.Attachment attachment
    ) {
        this.attachment = attachment;
    }

    public static Attachment create(
            com.julienvey.trello.domain.Attachment attachment
    ) {
        return new TrelloAttachment(attachment);
    }
}
