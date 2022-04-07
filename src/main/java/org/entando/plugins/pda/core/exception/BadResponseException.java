package org.entando.plugins.pda.core.exception;

public class BadResponseException extends InternalServerException {

    public BadResponseException() {
        super("org.entando.error.badFormatResponse");
    }

}
