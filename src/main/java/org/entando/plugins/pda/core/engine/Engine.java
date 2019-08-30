package org.entando.plugins.pda.core.engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.entando.plugins.pda.core.exception.EngineNotSupportedException;
import org.entando.plugins.pda.core.service.task.TaskService;

import java.util.Optional;

@Getter
@AllArgsConstructor
public abstract class Engine {
    protected String type;
    protected TaskService taskService;

    //protected CommentService commentService;

    public TaskService getTaskService() {
        return Optional.ofNullable(taskService)
                .orElseThrow(EngineNotSupportedException::new);
    }
}
