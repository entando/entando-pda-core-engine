package org.entando.plugins.pda.core.service.task;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.entando.plugins.pda.core.service.task.FakeTaskFormService.TASK_FORMS;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_DEFINITION_ID;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_1;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.TaskNotFoundException;
import org.entando.plugins.pda.core.model.form.Form;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FakeTaskFormServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private FakeTaskFormService taskFormService;

    @Before
    public void init() {
        taskFormService = new FakeTaskFormService();
    }

    @Test
    public void shouldGetTaskForm() {
        List<Form> result = taskFormService
                .getTaskForm(Connection.builder().build(), TASK_ID_1);

        assertThat(result).isEqualTo(TASK_FORMS);
    }

    @Test
    public void shouldThrowTaskNotFound() {
        expectedException.expect(TaskNotFoundException.class);
        taskFormService.getTaskForm(Connection.builder().build(), TASK_DEFINITION_ID);
    }

}
