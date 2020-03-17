package org.entando.plugins.pda.core.exception;

import org.entando.web.exception.BadRequestException;

public class SummaryFrequencyInvalidException extends BadRequestException {

    public SummaryFrequencyInvalidException(Throwable throwable) {
        super("org.entando.error.summary.frequency.invalid", throwable);
    }

    public SummaryFrequencyInvalidException() {
        this(null);
    }
}
