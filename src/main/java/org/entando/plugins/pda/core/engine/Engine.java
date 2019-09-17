package org.entando.plugins.pda.core.engine;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.entando.plugins.pda.core.exception.EngineNotSupportedException;
import org.entando.plugins.pda.core.service.task.TaskService;

@Getter
@AllArgsConstructor
public abstract class Engine {
    protected String type;
    protected TaskService taskService;

    public TaskService getTaskService() {
        return Optional.ofNullable(taskService)
                .orElseThrow(EngineNotSupportedException::new);
    }
}
