package org.entando.plugins.pda.core.exception;

import org.entando.web.exception.NotFoundException;

public class TaskNotFoundException extends NotFoundException {

    public TaskNotFoundException() {
        super("org.entando.error.task.notFound");
    }

}
