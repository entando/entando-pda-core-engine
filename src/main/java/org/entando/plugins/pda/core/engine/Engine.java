package org.entando.plugins.pda.core.engine;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.entando.plugins.pda.core.exception.EngineNotSupportedException;
import org.entando.plugins.pda.core.service.group.GroupService;
import org.entando.plugins.pda.core.service.process.ProcessFormService;
import org.entando.plugins.pda.core.service.process.ProcessService;
import org.entando.plugins.pda.core.service.task.TaskCommentService;
import org.entando.plugins.pda.core.service.task.TaskDefinitionService;
import org.entando.plugins.pda.core.service.task.TaskService;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Engine {

    protected String type;
    protected TaskService taskService;
    protected TaskDefinitionService taskDefinitionService;
    protected TaskCommentService taskCommentService;
    protected ProcessService processService;
    protected ProcessFormService processFormService;
    protected GroupService groupService;

    public Engine(String type) {
        this.type = type;
    }

    public TaskService getTaskService() {
        return Optional.ofNullable(taskService)
                .orElseThrow(EngineNotSupportedException::new);
    }

    public TaskDefinitionService getTaskDefinitionService() {
        return Optional.ofNullable(taskDefinitionService)
                .orElseThrow(EngineNotSupportedException::new);
    }

    public TaskCommentService getTaskCommentService() {
        return Optional.ofNullable(taskCommentService)
                .orElseThrow(EngineNotSupportedException::new);
    }

    public ProcessService getProcessService() {
        return Optional.ofNullable(processService)
                .orElseThrow(EngineNotSupportedException::new);
    }

    public ProcessFormService getProcessFormService() {
        return Optional.ofNullable(processFormService)
                .orElseThrow(EngineNotSupportedException::new);
    }

    public GroupService getGroupService() {
        return Optional.ofNullable(groupService)
                .orElseThrow(EngineNotSupportedException::new);
    }
}
