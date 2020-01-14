package org.entando.plugins.pda.core.engine;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.entando.plugins.pda.core.exception.EngineNotSupportedException;
import org.entando.plugins.pda.core.service.group.FakeGroupService;
import org.entando.plugins.pda.core.service.process.FakeProcessFormService;
import org.entando.plugins.pda.core.service.process.FakeProcessService;
import org.entando.plugins.pda.core.service.task.FakeTaskCommentService;
import org.entando.plugins.pda.core.service.task.FakeTaskDefinitionService;
import org.entando.plugins.pda.core.service.task.FakeTaskFormService;
import org.entando.plugins.pda.core.service.task.FakeTaskService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

@SuppressWarnings("PMD.TooManyMethods")
public class FakeEngineTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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
    public void shouldThrowExceptionForNullTaskService() {
        expectedException.expect(EngineNotSupportedException.class);

        Engine engine = FakeEngine.builder().build();

        engine.getTaskService();
    }

    @Test
    public void shouldThrowExceptionForNullTaskCommentService() {
        expectedException.expect(EngineNotSupportedException.class);

        Engine engine = FakeEngine.builder().build();

        engine.getTaskCommentService();
    }

    @Test
    public void shouldThrowExceptionForNullTaskDefinitionService() {
        expectedException.expect(EngineNotSupportedException.class);

        Engine engine = FakeEngine.builder().build();

        engine.getTaskDefinitionService();
    }

    @Test
    public void shouldThrowExceptionForNullGroupService() {
        expectedException.expect(EngineNotSupportedException.class);

        Engine engine = FakeEngine.builder().build();

        engine.getGroupService();
    }
}
