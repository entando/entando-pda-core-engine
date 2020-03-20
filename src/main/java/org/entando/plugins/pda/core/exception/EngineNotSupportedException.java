package org.entando.plugins.pda.core.exception;

import org.entando.web.exception.NotFoundException;

/**
 * Exception indicating a feature is not supported for the Engine.
 */
public class EngineNotSupportedException extends NotFoundException {

    public EngineNotSupportedException() {
        super("org.entando.error.engine.notSupported");
    }

}
