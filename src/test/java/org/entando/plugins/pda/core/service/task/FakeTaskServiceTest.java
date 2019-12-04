package org.entando.plugins.pda.core.service.task;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.Set;
import java.util.UUID;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.TaskNotFoundException;
import org.entando.plugins.pda.core.model.Task;
import org.entando.web.request.PagedListRequest;
import org.entando.web.response.PagedRestResponse;
import org.entando.web.response.SimpleRestResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.keycloak.representations.AccessToken;

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
                .list(Connection.builder().build(), dummyUser(), new PagedListRequest());

        assertThat(result.getPayload()).isNotEmpty();
    }

    @Test
    public void shouldGetTask() {
        Task task = taskService.get(Connection.builder().build(), dummyUser(), FakeTaskService.TASK_ID_1);

        assertThat(task).isNotNull();
        assertThat(task.getId()).isEqualTo(FakeTaskService.TASK_ID_1);
    }

    @Test
    public void shouldThrowNotFoundException() {
        expectedException.expect(TaskNotFoundException.class);

        taskService.get(Connection.builder().build(), dummyUser(), UUID.randomUUID().toString());
    }

    @Test
    public void shouldListTaskColumns() {
        SimpleRestResponse<Set<String>> response = taskService
                .listTaskColumns(Connection.builder().build(), dummyUser());

        assertThat(response.getPayload()).containsAll(FakeTaskService.TASK_COLUMNS);
    }

    private AuthenticatedUser dummyUser() {
        return new AuthenticatedUser("test", new AccessToken());
    }
}
