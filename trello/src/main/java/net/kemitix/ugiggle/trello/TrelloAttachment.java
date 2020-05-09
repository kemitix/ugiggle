package net.kemitix.ugiggle.trello;

import net.kemitix.ugiggle.service.Attachment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.file.Files;

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

    @Override
    public File getFileName() {
        return new File(URI.create(attachment.getUrl()).getPath());
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
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private URL getUrl() throws MalformedURLException {
        return URI.create(attachment.getUrl()).toURL();
    }
}
