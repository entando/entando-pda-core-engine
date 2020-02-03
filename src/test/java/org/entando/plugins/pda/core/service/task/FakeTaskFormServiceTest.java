package org.entando.plugins.pda.core.service.task;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.entando.plugins.pda.core.service.task.FakeTaskFormService.TASK_FORM_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_KEY_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_KEY_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.getDummyConnection;
import static org.entando.plugins.pda.core.utils.TestUtils.getDummyUser;
import static org.entando.plugins.pda.core.utils.TestUtils.randomStringId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
    private FakeTaskService taskService;

    @Before
    public void init() {
        taskService = new FakeTaskService();
        taskFormService = new FakeTaskFormService(taskService);
    }

    @Test
    public void shouldGetTaskForm() {
        Form result = taskFormService.get(getDummyConnection(), TASK_ID_1);

        assertThat(result).isEqualTo(TASK_FORM_1);
    }

    @Test
    public void shouldThrowTaskNotFound() {
        expectedException.expect(TaskNotFoundException.class);
        taskFormService.get(getDummyConnection(), randomStringId());
    }

    @Test
    public void shouldSubmitTaskForm() {
        //Given
        Map<String, Object> variables1 = new ConcurrentHashMap<>();
        variables1.put(TASK_FORM_PROP_KEY_1, TASK_FORM_PROP_1);
        variables1.put(TASK_FORM_PROP_KEY_2, TASK_FORM_PROP_2);

        Map<String, Object> expected = new ConcurrentHashMap<>();
        expected.put(TASK_FORM_ID_1, variables1);

        //When
        String id = taskFormService.submit(getDummyConnection(), getDummyUser(), TASK_ID_1, expected);

        //Then
        assertThat(id).isEqualTo(TASK_ID_1);
        assertThat(taskService.get(getDummyConnection(), getDummyUser(), TASK_ID_1).getOutputData())
                .isEqualTo(expected);
    }

    @Test
    public void shouldThrowTaskNotFoundWhenSubmittingForm() {
        expectedException.expect(TaskNotFoundException.class);
        taskFormService.submit(getDummyConnection(), getDummyUser(), randomStringId(),
                new ConcurrentHashMap<>());
    }

}
