package org.entando.plugins.pda.core.exception;

import org.entando.web.exception.NotFoundException;

public class ProcessDefinitionNotFoundException extends NotFoundException {

    public ProcessDefinitionNotFoundException(Throwable e) {
        super("org.entando.error.processDefinition.notFound", e);
    }

    public ProcessDefinitionNotFoundException() {
        this(null);
    }
}
