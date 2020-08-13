package net.kemitix.ugiggle.service;

import net.kemitix.ugiggle.trello.Attachment;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class ConversionService {

    @Inject
    Instance<AttachmentConverter> attachmentConverters;

    public Attachment convert(Attachment attachment) {
        return attachmentConverters.stream()
                .filter(converter -> converter.canHandle(attachment))
                .findFirst()
                .flatMap(converter -> converter.convert(attachment))
                .orElse(attachment);
    }

}
