package org.entando.plugins.pda.core.exception;


public class EngineNotSupportedException extends NotFoundException {

    public EngineNotSupportedException() {
        super("org.entando.error.engine.notSupported");
    }

}
