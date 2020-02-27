package org.entando.plugins.pda.core.service.task;

import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_NAME_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_NAME_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_SUBJECT_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_SUBJECT_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.TaskNotFoundException;
import org.entando.plugins.pda.core.model.Task;
import org.entando.web.request.PagedListRequest;
import org.entando.web.response.PagedMetadata;
import org.entando.web.response.PagedRestResponse;
import org.springframework.stereotype.Service;

@Service
public class FakeTaskService implements TaskService {

    public static final Map<String, Task> TASKS = new ConcurrentHashMap<>();

    static {
        for (Task task : createTasks()) {
            TASKS.put(task.getId(), task);
        }
    }

    @Override
    public PagedRestResponse<Task> list(Connection connection, AuthenticatedUser user,
            PagedListRequest restListRequest, String search, List<String> groups) {
        return new PagedRestResponse<>(new PagedMetadata<>(restListRequest, new ArrayList<>(
                TASKS.values().stream()
                    .filter(t -> search == null
                            || t.getName().contains(search.replace("*", "").trim()))
                    .collect(Collectors.toList()))));
    }

    @Override
    public Task get(Connection connection, AuthenticatedUser user, String id) {
        return TASKS.values().stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(TaskNotFoundException::new);
    }

    private static List<Task> createTasks() {
        List<Task> result = new ArrayList<>();

        result.add(Task.taskBuilder()
                .id(TASK_ID_1)
                .name(TASK_NAME_1)
                .inputData(Collections.singletonMap("subject", TASK_SUBJECT_1))
                .build());

        result.add(Task.taskBuilder()
                .id(TASK_ID_2)
                .name(TASK_NAME_2)
                .inputData(Collections.singletonMap("subject", TASK_SUBJECT_2))
                .build());

        return result;
    }
}
