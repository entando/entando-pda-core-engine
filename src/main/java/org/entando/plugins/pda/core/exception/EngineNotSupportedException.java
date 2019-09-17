package org.entando.plugins.pda.core.exception;

import org.entando.web.exception.NotFoundException;

public class EngineNotSupportedException extends NotFoundException {

    public EngineNotSupportedException() {
        super("org.entando.error.engine.notSupported");
    }

}
