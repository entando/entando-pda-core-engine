package org.entando.plugins.pda.core.exception;

public class DataRepositoryNotFoundException extends NotFoundException {

    public DataRepositoryNotFoundException(Throwable e) {
        super("org.entando.error.summary.data.repository.notFound", e);
    }

    public DataRepositoryNotFoundException() {
        this(null);
    }

}
