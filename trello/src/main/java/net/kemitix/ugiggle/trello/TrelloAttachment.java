package net.kemitix.ugiggle.trello;

import com.julienvey.trello.domain.Card;
import net.kemitix.ugiggle.service.Attachment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrelloAttachment implements Attachment {
    private static final String[] EXTENSIONS = new String[]{"doc", "docx", "odt"};
    private final com.julienvey.trello.domain.Attachment attachment;
    private final Card card;

    private TrelloAttachment(
            com.julienvey.trello.domain.Attachment attachment,
            Card card
    ) {
        this.attachment = attachment;
        this.card = card;
    }

    public static Attachment create(
            com.julienvey.trello.domain.Attachment attachment,
            Card card
    ) {
        return new TrelloAttachment(attachment, card);
    }

    @Override
    public File getFileName() {
        return new File(String.format("%s.%s", card.getName(), extension()));
    }

    private String extension() {
        URI uri = URI.create(attachment.getUrl());
        String path = uri.getPath();
        for (String ex : EXTENSIONS) {
            if (path.endsWith("." + ex)) {
                return ex;
            }
        }
        return "";
    }

    @Override
    public File download() {
        try (var source = Channels.newChannel(getUrl().openStream());){
            var file = Files.createTempFile("trello", "attachment").toFile();
            try (var channel = new FileOutputStream(file).getChannel()) {
                channel.transferFrom(source, 0, Long.MAX_VALUE);
                return file;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URL getUrl() throws MalformedURLException {
        return URI.create(attachment.getUrl()).toURL();
    }
}
