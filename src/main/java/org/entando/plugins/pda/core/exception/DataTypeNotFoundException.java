package org.entando.plugins.pda.core.exception;

import org.entando.web.exception.NotFoundException;

public class DataTypeNotFoundException extends NotFoundException {

    public DataTypeNotFoundException(Throwable e) {
        super("org.entando.error.summary.data.type.notFound", e);
    }

    public DataTypeNotFoundException() {
        this(null);
    }

}
