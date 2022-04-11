package org.entando.plugins.pda.core.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.entando.plugins.pda.core.exception.EngineNotSupportedException;
import org.entando.plugins.pda.core.service.group.FakeGroupService;
import org.entando.plugins.pda.core.service.process.FakeProcessFormService;
import org.entando.plugins.pda.core.service.process.FakeProcessInstanceService;
import org.entando.plugins.pda.core.service.process.FakeProcessService;
import org.entando.plugins.pda.core.service.task.FakeTaskCommentService;
import org.entando.plugins.pda.core.service.task.FakeTaskDefinitionService;
import org.entando.plugins.pda.core.service.task.FakeTaskFormService;
import org.entando.plugins.pda.core.service.task.FakeTaskLifecycleBulkService;
import org.entando.plugins.pda.core.service.task.FakeTaskLifecycleService;
import org.entando.plugins.pda.core.service.task.FakeTaskService;
import org.junit.jupiter.api.Test;

@SuppressWarnings("PMD.TooManyMethods")
public class FakeEngineTest {

    @Test
    public void shouldReturnCorrectEngineType() {
        Engine engine = FakeEngine.builder().build();

        assertThat(engine.getType()).isEqualTo(FakeEngine.TYPE);
    }

    @Test
    public void shouldReturnTaskService() {
        FakeTaskService taskService = new FakeTaskService();
        Engine engine = FakeEngine.builder().taskService(taskService).build();

        assertThat(engine.getTaskService()).isEqualTo(taskService);
    }

    @Test
    public void shouldReturnTaskCommentService() {
        FakeTaskCommentService taskCommentService = new FakeTaskCommentService();
        Engine engine = FakeEngine.builder().taskCommentService(taskCommentService).build();

        assertThat(engine.getTaskCommentService()).isEqualTo(taskCommentService);
    }

    @Test
    public void shouldReturnTaskDefinitionService() {
        FakeTaskDefinitionService taskDefinitionService = new FakeTaskDefinitionService();
        Engine engine = FakeEngine.builder().taskDefinitionService(taskDefinitionService).build();

        assertThat(engine.getTaskDefinitionService()).isEqualTo(taskDefinitionService);
    }

    @Test
    public void shouldReturnTaskFormService() {
        FakeTaskFormService taskFormService = new FakeTaskFormService(null);
        Engine engine = FakeEngine.builder().taskFormService(taskFormService).build();

        assertThat(engine.getTaskFormService()).isEqualTo(taskFormService);
    }

    @Test
    public void shouldReturnProcessService() {
        FakeProcessService processService = new FakeProcessService();
        Engine engine = FakeEngine.builder().processService(processService).build();

        assertThat(engine.getProcessService()).isEqualTo(processService);
    }

    @Test
    public void shouldReturnProcessFormService() {
        FakeProcessFormService processFormService = new FakeProcessFormService();
        Engine engine = FakeEngine.builder().processFormService(processFormService).build();

        assertThat(engine.getProcessFormService()).isEqualTo(processFormService);
    }

    @Test
    public void shouldReturnGroupService() {
        FakeGroupService groupService = new FakeGroupService();

        Engine engine = FakeEngine.builder().groupService(groupService).build();

        assertThat(engine.getGroupService()).isEqualTo(groupService);
    }

    @Test
    public void shouldReturnTaskLifecycleService() {
        FakeTaskLifecycleService taskLifecycleService = new FakeTaskLifecycleService();

        Engine engine = FakeEngine.builder().taskLifecycleService(taskLifecycleService).build();

        assertThat(engine.getTaskLifecycleService()).isEqualTo(taskLifecycleService);
    }

    @Test
    public void shouldReturnTaskLifecycleBulkService() {
        FakeTaskLifecycleBulkService taskLifecycleBulkService = new FakeTaskLifecycleBulkService();

        Engine engine = FakeEngine.builder().taskLifecycleBulkService(taskLifecycleBulkService).build();

        assertThat(engine.getTaskLifecycleBulkService()).isEqualTo(taskLifecycleBulkService);
    }

    @Test
    public void shouldThrowExceptionForNullTaskService() {
        Engine engine = FakeEngine.builder().build();

        assertThrows(EngineNotSupportedException.class, engine::getTaskService);
    }

    @Test
    public void shouldThrowExceptionForNullTaskCommentService() {
        Engine engine = FakeEngine.builder().build();

        assertThrows(EngineNotSupportedException.class, engine::getTaskCommentService);
    }

    @Test
    public void shouldThrowExceptionForNullTaskDefinitionService() {
        Engine engine = FakeEngine.builder().build();

        assertThrows(EngineNotSupportedException.class, engine::getTaskDefinitionService);
    }

    @Test
    public void shouldThrowExceptionForNullGroupService() {
        Engine engine = FakeEngine.builder().build();

        assertThrows(EngineNotSupportedException.class, engine::getGroupService);
    }

    @Test
    public void shouldReturnProcessInstanceService() {
        FakeProcessInstanceService processInstanceService = new FakeProcessInstanceService();

        Engine engine = FakeEngine.builder().processInstanceService(processInstanceService).build();

        assertThat(engine.getProcessInstanceService()).isEqualTo(processInstanceService);
    }

    @Test
    public void shouldThrowExceptionForNullProcessInstanceService() {
        Engine engine = FakeEngine.builder().build();

        assertThrows(EngineNotSupportedException.class, engine::getProcessInstanceService);
    }
}
