package net.kemitix.ugiggle.converter.odt;

import net.kemitix.ugiggle.service.Attachment;
import net.kemitix.ugiggle.service.AttachmentConverter;
import net.kemitix.ugiggle.service.LocalAttachment;
import org.odftoolkit.odfdom.converter.xhtml.XHTMLConverter;
import org.odftoolkit.odfdom.converter.xhtml.XHTMLOptions;
import org.odftoolkit.odfdom.doc.OdfTextDocument;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.*;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class OdtToHtmlAttachmentConverter
        implements AttachmentConverter {

    private static final Logger LOG =
            Logger.getLogger(
                    OdtToHtmlAttachmentConverter.class.getName());

    @Override
    public boolean canHandle(Attachment attachment) {
        return attachment.getFileName()
                .getName()
                .endsWith(".odt");
    }

    @Override
    public Optional<Attachment> convert(Attachment attachment) {
        String name = attachment.getFileName().getName();
        LOG.info("Converting from " + name);
        String htmlName = name.substring(0, name.length() - 3) + "html";
        LOG.info("Converting  to  " + htmlName);
        try {
            // 1) Load ODT into ODFDOM OdfTextDocument
            InputStream in = new FileInputStream(attachment.getFileName());
            OdfTextDocument document = OdfTextDocument.loadDocument(in);
            // 2) Prepare XHTML options (here we set the IURIResolver to load images from a "Pictures" folder)
            XHTMLOptions options = XHTMLOptions.create();
            // 3) Convert OdfTextDocument to XHTML
            File htmlFile = new File(htmlName);
            OutputStream out = new FileOutputStream(htmlFile);
            XHTMLConverter.getInstance().convert(document, out, options);
            // Convert to Attachment
            LOG.info("Converted   to  " + htmlName);
            return Optional.of(new LocalAttachment(htmlFile));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
