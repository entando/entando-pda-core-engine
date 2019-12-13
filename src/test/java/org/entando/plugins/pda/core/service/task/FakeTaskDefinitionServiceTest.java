package org.entando.plugins.pda.core.service.task;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.entando.plugins.pda.core.service.task.FakeTaskDefinitionService.TASK_COLUMNS;
import static org.entando.plugins.pda.core.utils.TestUtils.getDummyUser;

import java.util.Set;
import org.entando.plugins.pda.core.engine.Connection;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FakeTaskDefinitionServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private FakeTaskDefinitionService taskService;

    @Before
    public void init() {
        taskService = new FakeTaskDefinitionService();
    }

    @Test
    public void shouldListTaskColumns() {
        Set<String> response = taskService
                .listColumns(Connection.builder().build(), getDummyUser());

        assertThat(response).containsAll(TASK_COLUMNS);
    }
}
