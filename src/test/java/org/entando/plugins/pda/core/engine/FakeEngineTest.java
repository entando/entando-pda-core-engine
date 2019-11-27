package org.entando.plugins.pda.core.engine;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.entando.plugins.pda.core.exception.EngineNotSupportedException;
import org.entando.plugins.pda.core.service.process.FakeProcessService;
import org.entando.plugins.pda.core.service.task.FakeTaskService;
import org.entando.plugins.pda.core.service.task.ProcessService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FakeEngineTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldReturnTaskService() {
        FakeTaskService taskService = new FakeTaskService();
        Engine engine = new FakeEngine(taskService, null);

        assertThat(engine.getTaskService()).isEqualTo(taskService);
    }

    @Test
    public void shouldReturnProcessService() {
        FakeProcessService processService = new FakeProcessService();
        Engine engine = new FakeEngine(null, processService);

        assertThat(engine.getProcessService()).isEqualTo(processService);
    }

    @Test
    public void shouldThrowExceptionForNullService() {
        expectedException.expect(EngineNotSupportedException.class);

        Engine engine = new FakeEngine(null, null);

        engine.getTaskService();
    }

    @Test
    public void shouldReturnCorrectEngineType() {
        Engine engine = new FakeEngine(null,null);

        assertThat(engine.getType()).isEqualTo(FakeEngine.TYPE);
    }
}
