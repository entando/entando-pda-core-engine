package org.entando.plugins.pda.core.service.task;

import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.Task;
import org.entando.web.request.PagedListRequest;
import org.entando.web.response.PagedMetadata;
import org.entando.web.response.PagedRestResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeTaskService implements TaskService {
    public static final String TASK_ID_1 = "1";
    public static final String TASK_NAME_1 = "Task 1";
    public static final String TASK_SUBJECT_1 = "Task Subject 1";
    public static final String TASK_DESCRIPTION_1 = "Task description #1 describing a task's details";
    public static final String TASK_STATUS_1 = "Pending";
    public static final Integer TASK_PRIORITY_1 = 10;
    public static final Boolean TASK_SKIPABLE_1 = false;
    public static final String TASK_PROCESS_ID_1 = "processId1";
    public static final String TASK_PROCESS_INSTANCE_ID_1 = "processInstanceId1";

    public static final String TASK_ID_2 = "2";
    public static final String TASK_NAME_2 = "Task 2";
    public static final String TASK_SUBJECT_2 = "Task Subject 2";
    public static final String TASK_DESCRIPTION_2 = "Task description #2 describing a task's details";
    public static final String TASK_STATUS_2 = "Complete";
    public static final Integer TASK_PRIORITY_2 = 5;
    public static final Boolean TASK_SKIPABLE_2 = true;
    public static final String TASK_PROCESS_ID_2 = "processId2";
    public static final String TASK_PROCESS_INSTANCE_ID_2 = "processInstanceId2";

    private List<Task> tasks = createTasks();

    @Override
    public PagedRestResponse<Task> list(Connection connection, PagedListRequest restListRequest) {
        return new PagedRestResponse<>(new PagedMetadata<>(restListRequest, tasks));
    }

    private List<Task> createTasks() {
        List<Task> result = new ArrayList<>();

        result.add(Task.builder()
                .id(TASK_ID_1)
                .name(TASK_NAME_1)
                .subject(TASK_SUBJECT_1)
                .description(TASK_DESCRIPTION_1)
                .status(TASK_STATUS_1)
                .priority(TASK_PRIORITY_1)
                .skipable(TASK_SKIPABLE_1)
                .processId(TASK_PROCESS_ID_1)
                .processInstanceId(TASK_PROCESS_INSTANCE_ID_1)
                .build());

        result.add(Task.builder()
                .id(TASK_ID_2)
                .name(TASK_NAME_2)
                .subject(TASK_SUBJECT_2)
                .description(TASK_DESCRIPTION_2)
                .status(TASK_STATUS_2)
                .priority(TASK_PRIORITY_2)
                .skipable(TASK_SKIPABLE_2)
                .processId(TASK_PROCESS_ID_2)
                .processInstanceId(TASK_PROCESS_INSTANCE_ID_2)
                .build());

        return result;
    }
}
