package org.entando.plugins.pda.core.engine;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.entando.plugins.pda.core.exception.EngineNotSupportedException;
import org.entando.plugins.pda.core.service.group.GroupService;
import org.entando.plugins.pda.core.service.process.ProcessFormService;
import org.entando.plugins.pda.core.service.process.ProcessService;
import org.entando.plugins.pda.core.service.task.TaskAttachmentService;
import org.entando.plugins.pda.core.service.task.TaskCommentService;
import org.entando.plugins.pda.core.service.task.TaskDefinitionService;
import org.entando.plugins.pda.core.service.task.TaskFormService;
import org.entando.plugins.pda.core.service.task.TaskLifecycleBulkService;
import org.entando.plugins.pda.core.service.task.TaskLifecycleService;
import org.entando.plugins.pda.core.service.task.TaskService;

/**
 * Represents a BPM engine and expose the services that are available for that specific implementation. It is
 * intended to be inherited and the subclass should provide the real implementation for each service by calling the
 * superclass constructor with the service implementations as arguments. If any service is not supported, a null value
 * should be passed to the corresponding constructor argument.
 * <p>
 * The type attribute should be a unique identifier for the engine being implemented and it is going to be used to map
 * the {@link Connection} to the engine.
 * <p>
 * All the methods that return a service can throw a {@link EngineNotSupportedException} if that service is not
 * available for the engine implementation. In other words, if a null value was provided by the subclass.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Engine {

    protected String type;
    protected TaskService taskService;
    protected TaskDefinitionService taskDefinitionService;
    protected TaskCommentService taskCommentService;
    protected TaskAttachmentService taskAttachmentService;
    protected TaskFormService taskFormService;
    protected TaskLifecycleService taskLifecycleService;
    protected TaskLifecycleBulkService taskLifecycleBulkService;
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

    public TaskAttachmentService getTaskAttachmentService() {
        return Optional.ofNullable(taskAttachmentService)
                .orElseThrow(EngineNotSupportedException::new);
    }

    public TaskFormService getTaskFormService() {
        return Optional.ofNullable(taskFormService)
                .orElseThrow(EngineNotSupportedException::new);
    }

    public TaskLifecycleService getTaskLifecycleService() {
        return Optional.ofNullable(taskLifecycleService)
                .orElseThrow(EngineNotSupportedException::new);
    }

    public TaskLifecycleBulkService getTaskLifecycleBulkService() {
        return Optional.ofNullable(taskLifecycleBulkService)
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
