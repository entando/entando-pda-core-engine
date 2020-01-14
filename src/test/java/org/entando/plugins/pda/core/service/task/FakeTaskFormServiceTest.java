package org.entando.plugins.pda.core.service.task;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.entando.plugins.pda.core.service.task.FakeTaskFormService.TASK_FORMS;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_ID_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_3;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_KEY_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_KEY_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_KEY_3;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.getDummyConnection;
import static org.entando.plugins.pda.core.utils.TestUtils.getDummyUser;
import static org.entando.plugins.pda.core.utils.TestUtils.randomStringId;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.entando.plugins.pda.core.exception.TaskNotFoundException;
import org.entando.plugins.pda.core.model.Task;
import org.entando.plugins.pda.core.model.form.Form;
import org.entando.plugins.pda.core.model.task.CreateTaskFormSubmissionRequest;
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
        FakeTaskService taskService = new FakeTaskService();
        taskFormService = new FakeTaskFormService(taskService);
    }

    @Test
    public void shouldGetTaskForm() {
        List<Form> result = taskFormService.get(getDummyConnection(), TASK_ID_1);

        assertThat(result).isEqualTo(TASK_FORMS);
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

        Map<String, Object> variables2 = new ConcurrentHashMap<>();
        variables2.put(TASK_FORM_PROP_KEY_3, TASK_FORM_PROP_3);

        CreateTaskFormSubmissionRequest request = CreateTaskFormSubmissionRequest.builder()
                .form(TASK_FORM_ID_1, variables1)
                .form(TASK_FORM_ID_2, variables2)
                .build();

        Map<String, Object> expected = new ConcurrentHashMap<>();
        request.getForms().forEach((key, value) -> value.forEach(expected::put));

        //When
        Task task = taskFormService.submit(getDummyConnection(), getDummyUser(), TASK_ID_1, request);

        //Then
        assertThat(task.getOutputData()).isEqualTo(expected);
    }

    @Test
    public void shouldThrowTaskNotFoundWhenSubmittingForm() {
        expectedException.expect(TaskNotFoundException.class);
        taskFormService.submit(getDummyConnection(), getDummyUser(), randomStringId(),
                CreateTaskFormSubmissionRequest.builder().build());
    }

}
