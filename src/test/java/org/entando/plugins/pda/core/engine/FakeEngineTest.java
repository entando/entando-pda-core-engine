package org.entando.plugins.pda.core.engine;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.entando.plugins.pda.core.exception.EngineNotSupportedException;
import org.entando.plugins.pda.core.service.group.FakeGroupService;
import org.entando.plugins.pda.core.service.process.FakeProcessService;
import org.entando.plugins.pda.core.service.task.FakeTaskService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FakeEngineTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldReturnTaskService() {
        FakeTaskService taskService = new FakeTaskService();
        Engine engine = new FakeEngine(taskService, null, null);

        assertThat(engine.getTaskService()).isEqualTo(taskService);
    }

    @Test
    public void shouldReturnProcessService() {
        FakeProcessService processService = new FakeProcessService();
        Engine engine = new FakeEngine(null, processService, null);

        assertThat(engine.getProcessService()).isEqualTo(processService);
    }

    @Test
    public void shouldThrowExceptionForNullService() {
        expectedException.expect(EngineNotSupportedException.class);

        Engine engine = new FakeEngine(null, null, null);

        engine.getTaskService();
    }

    @Test
    public void shouldReturnCorrectEngineType() {
        FakeTaskService taskService = new FakeTaskService();
        Engine engine = new FakeEngine(taskService, null, null);

        assertThat(engine.getType()).isEqualTo(FakeEngine.TYPE);
    }

    @Test
    public void shouldReturnGroupService() {
        FakeGroupService groupService = new FakeGroupService();

        Engine engine = new FakeEngine(null, null, groupService);

        assertThat(engine.getGroupService()).isEqualTo(groupService);
    }

    @Test
    public void shouldThrowExceptionForNullGroupService() {
        expectedException.expect(EngineNotSupportedException.class);

        Engine engine = new FakeEngine(new FakeTaskService(), null, null);

        engine.getGroupService();
    }
}
