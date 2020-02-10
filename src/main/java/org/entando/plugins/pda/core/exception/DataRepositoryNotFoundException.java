package org.entando.plugins.pda.core.exception;

import org.entando.web.exception.NotFoundException;

public class DataRepositoryNotFoundException extends NotFoundException {

    public DataRepositoryNotFoundException(Throwable e) {
        super("org.entando.error.summary.data.repository.notFound", e);
    }

    public DataRepositoryNotFoundException() {
        this(null);
    }

}
