package org.entando.plugins.pda.core.exception;

import org.entando.web.exception.NotFoundException;

public class AttachmentNotFoundException extends NotFoundException {

    public AttachmentNotFoundException(Throwable e) {
        super("org.entando.error.attachment.notFound", e);
    }

    public AttachmentNotFoundException() {
        this(null);
    }

}
