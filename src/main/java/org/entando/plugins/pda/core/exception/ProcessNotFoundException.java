package org.entando.plugins.pda.core.exception;

import org.entando.web.exception.NotFoundException;

public class ProcessNotFoundException extends NotFoundException {

    public ProcessNotFoundException(Throwable e) {
        super("org.entando.error.process.notFound", e);
    }

    public ProcessNotFoundException() {
        this(null);
    }
}
