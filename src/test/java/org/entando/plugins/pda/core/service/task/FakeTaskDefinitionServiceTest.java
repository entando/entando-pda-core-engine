package org.entando.plugins.pda.core.service.task;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.entando.plugins.pda.core.service.task.FakeTaskDefinitionService.TASK_COLUMNS;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FakeTaskDefinitionServiceTest {


    private FakeTaskDefinitionService taskService;

    @BeforeEach
    public void init() {
        taskService = new FakeTaskDefinitionService();
    }

    @Test
    public void shouldListTaskColumns() {
        Set<String> response = taskService
                .listColumns();

        assertThat(response).containsAll(TASK_COLUMNS);
    }
}
