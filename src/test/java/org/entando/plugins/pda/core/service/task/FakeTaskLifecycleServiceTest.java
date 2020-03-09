package org.entando.plugins.pda.core.service.task;

import static org.assertj.core.api.Assertions.assertThat;
import static org.entando.plugins.pda.core.service.task.FakeTaskLifecycleService.ASSIGN_ACTION;
import static org.entando.plugins.pda.core.service.task.FakeTaskLifecycleService.CLAIM_ACTION;
import static org.entando.plugins.pda.core.service.task.FakeTaskLifecycleService.COMPLETE_ACTION;
import static org.entando.plugins.pda.core.service.task.FakeTaskLifecycleService.PAUSE_ACTION;
import static org.entando.plugins.pda.core.service.task.FakeTaskLifecycleService.START_ACTION;
import static org.entando.plugins.pda.core.service.task.FakeTaskLifecycleService.UNCLAIM_ACTION;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

public class FakeTaskLifecycleServiceTest {

    private FakeTaskLifecycleService fakeTaskLifecycleService;

    @Before
    public void setUp() {
        fakeTaskLifecycleService = new FakeTaskLifecycleService();
    }

    @Test
    public void shouldClaimTask() {
        String taskId = RandomStringUtils.randomNumeric(5);
        fakeTaskLifecycleService.claim(null, null, taskId);

        assertThat(fakeTaskLifecycleService.getRecordedActions().get(taskId)).contains(CLAIM_ACTION);
    }

    @Test
    public void shouldUnclaimTask() {
        String taskId = RandomStringUtils.randomNumeric(5);
        fakeTaskLifecycleService.unclaim(null, null, taskId);

        assertThat(fakeTaskLifecycleService.getRecordedActions().get(taskId)).contains(UNCLAIM_ACTION);
    }

    @Test
    public void shouldAssignTask() {
        String taskId = RandomStringUtils.randomNumeric(5);
        fakeTaskLifecycleService.assign(null, null, taskId, "test");

        assertThat(fakeTaskLifecycleService.getRecordedActions().get(taskId)).contains(ASSIGN_ACTION);
    }

    @Test
    public void shouldStartTask() {
        String taskId = RandomStringUtils.randomNumeric(5);
        fakeTaskLifecycleService.start(null, null, taskId);

        assertThat(fakeTaskLifecycleService.getRecordedActions().get(taskId)).contains(START_ACTION);
    }

    @Test
    public void shouldPauseTask() {
        String taskId = RandomStringUtils.randomNumeric(5);
        fakeTaskLifecycleService.pause(null, null, taskId);

        assertThat(fakeTaskLifecycleService.getRecordedActions().get(taskId)).contains(PAUSE_ACTION);
    }

    @Test
    public void shouldCompleteTask() {
        String taskId = RandomStringUtils.randomNumeric(5);
        fakeTaskLifecycleService.complete(null, null, taskId);

        assertThat(fakeTaskLifecycleService.getRecordedActions().get(taskId)).contains(COMPLETE_ACTION);
    }
}
