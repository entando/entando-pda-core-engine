package org.entando.plugins.pda.core.exception;

import org.entando.web.exception.NotFoundException;

public class EngineNotFoundException extends NotFoundException {

    public EngineNotFoundException() {
        super("org.entando.error.engine.notFound");
    }

}
