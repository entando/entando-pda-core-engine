package org.entando.plugins.pda.core.engine;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.entando.plugins.pda.core.exception.EngineNotSupportedException;
import org.entando.plugins.pda.core.service.group.GroupService;
import org.entando.plugins.pda.core.service.task.TaskService;

@Getter
@AllArgsConstructor
public abstract class Engine {

    protected String type;
    protected TaskService taskService;
    protected GroupService groupService;

    public TaskService getTaskService() {
        return Optional.ofNullable(taskService)
                .orElseThrow(EngineNotSupportedException::new);
    }

    public GroupService getGroupService() {
        return Optional.ofNullable(groupService)
                .orElseThrow(EngineNotSupportedException::new);
    }
}
