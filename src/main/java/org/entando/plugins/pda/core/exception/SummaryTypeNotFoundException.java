package org.entando.plugins.pda.core.exception;

import org.entando.web.exception.NotFoundException;

public class SummaryTypeNotFoundException extends NotFoundException {

    public static final String MESSAGE_KEY = "org.entando.error.summary.type.notFound";

    public SummaryTypeNotFoundException(Throwable e) {
        super(MESSAGE_KEY, e);
    }

    public SummaryTypeNotFoundException() {
        this(null);
    }

}
