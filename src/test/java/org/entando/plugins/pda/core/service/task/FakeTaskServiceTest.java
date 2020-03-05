package org.entando.plugins.pda.core.service.task;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.getDummyUser;

import java.util.UUID;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.TaskNotFoundException;
import org.entando.plugins.pda.core.model.Task;
import org.entando.web.request.PagedListRequest;
import org.entando.web.response.PagedRestResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FakeTaskServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private FakeTaskService taskService;

    @Before
    public void init() {
        taskService = new FakeTaskService();
    }

    @Test
    public void shouldListTasks() {
        PagedRestResponse<Task> result = taskService
                .list(Connection.builder().build(), getDummyUser(), new PagedListRequest(), null, null);

        assertThat(result.getPayload()).hasSize(2);
    }

    @Test
    public void shouldSearchTaskList() {
        PagedRestResponse<Task> result = taskService
                .list(Connection.builder().build(), getDummyUser(), new PagedListRequest(), "* 1", null);

        assertThat(result.getPayload()).hasSize(1);
    }

    @Test
    public void shouldGetTask() {
        Task task = taskService.get(Connection.builder().build(), getDummyUser(), TASK_ID_1);

        assertThat(task).isNotNull();
        assertThat(task.getId()).isEqualTo(TASK_ID_1);
    }

    @Test
    public void shouldThrowNotFoundException() {
        expectedException.expect(TaskNotFoundException.class);

        taskService.get(Connection.builder().build(), getDummyUser(), UUID.randomUUID().toString());
    }
}
