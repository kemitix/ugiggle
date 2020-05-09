package net.kemitix.ugiggle.service;

import java.io.File;

public interface Attachment {
    File getFileName();

    File download();
}
