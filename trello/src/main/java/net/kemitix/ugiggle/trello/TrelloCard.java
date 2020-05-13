package net.kemitix.ugiggle.trello;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Card;
import net.kemitix.ugiggle.AttachmentDirectory;
import net.kemitix.ugiggle.service.Attachment;
import net.kemitix.ugiggle.service.Submission;
import net.kemitix.ugiggle.service.UGiggleConfig;

import java.util.stream.Stream;

public class TrelloCard implements Submission {
    private final com.julienvey.trello.domain.Card tcard;
    private final Trello trello;
    private AttachmentDirectory attachmentDir;

    private TrelloCard(
            Card tcard,
            Trello trello,
            AttachmentDirectory attachmentDir
    ) {
        this.tcard = tcard;
        this.trello = trello;
        this.attachmentDir = attachmentDir;
    }

    public static TrelloCard create(
            Card tcard,
            Trello trello,
            AttachmentDirectory attachmentDir
    ) {
        return new TrelloCard(tcard, trello, attachmentDir);
    }

    public Stream<Attachment> findAttachments() {
        return trello.getCardAttachments(tcard.getId()).stream()
                .map(attachment -> TrelloAttachment
                        .create(attachment, tcard, attachmentDir));
    }

    public String getName() {
        return tcard.getName();
    }
}
