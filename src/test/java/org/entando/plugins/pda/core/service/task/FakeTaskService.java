package org.entando.plugins.pda.core.service.task;

import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_NAME_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_NAME_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_SUBJECT_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_SUBJECT_2;

import java.util.ArrayList;
import java.util.List;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.TaskNotFoundException;
import org.entando.plugins.pda.core.model.FakeTask;
import org.entando.plugins.pda.core.model.Task;
import org.entando.web.request.PagedListRequest;
import org.entando.web.response.PagedMetadata;
import org.entando.web.response.PagedRestResponse;
import org.springframework.stereotype.Service;

@Service
public class FakeTaskService implements TaskService {

    @Override
    public PagedRestResponse<Task> list(Connection connection, AuthenticatedUser user,
            PagedListRequest restListRequest) {
        return new PagedRestResponse<>(new PagedMetadata<>(restListRequest, createTasks()));
    }

    @Override
    public Task get(Connection connection, AuthenticatedUser user, String id) {
        for (Task task : createTasks()) {
            if (task.getId().equals(id)) {
                return task;
            }
        }

        throw new TaskNotFoundException();
    }

    private List<Task> createTasks() {
        List<Task> result = new ArrayList<>();

        result.add(FakeTask.builder()
                .id(TASK_ID_1)
                .name(TASK_NAME_1)
                .extraProperty("subject", TASK_SUBJECT_1)
                .build());

        result.add(FakeTask.builder()
                .id(TASK_ID_2)
                .name(TASK_NAME_2)
                .extraProperty("subject", TASK_SUBJECT_2)
                .build());

        return result;
    }
}
