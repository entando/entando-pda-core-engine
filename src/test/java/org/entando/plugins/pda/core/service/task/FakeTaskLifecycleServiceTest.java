package org.entando.plugins.pda.core.service.task;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.entando.plugins.pda.core.service.task.FakeTaskLifecycleService.ASSIGN_ACTION;
import static org.entando.plugins.pda.core.service.task.FakeTaskLifecycleService.CLAIM_ACTION;
import static org.entando.plugins.pda.core.service.task.FakeTaskLifecycleService.COMPLETE_ACTION;
import static org.entando.plugins.pda.core.service.task.FakeTaskLifecycleService.PAUSE_ACTION;
import static org.entando.plugins.pda.core.service.task.FakeTaskLifecycleService.RESUME_ACTION;
import static org.entando.plugins.pda.core.service.task.FakeTaskLifecycleService.START_ACTION;
import static org.entando.plugins.pda.core.service.task.FakeTaskLifecycleService.UNCLAIM_ACTION;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FakeTaskLifecycleServiceTest {

    private FakeTaskLifecycleService fakeTaskLifecycleService;

    @BeforeEach
    public void setUp() {
        fakeTaskLifecycleService = new FakeTaskLifecycleService();
    }

    @Test
    public void shouldClaimTask() {
        String taskId = randomNumeric(5);
        fakeTaskLifecycleService.claim(null, null, taskId);

        assertThat(fakeTaskLifecycleService.getRecordedActions().get(taskId)).contains(CLAIM_ACTION);
    }

    @Test
    public void shouldUnclaimTask() {
        String taskId = randomNumeric(5);
        fakeTaskLifecycleService.unclaim(null, null, taskId);

        assertThat(fakeTaskLifecycleService.getRecordedActions().get(taskId)).contains(UNCLAIM_ACTION);
    }

    @Test
    public void shouldAssignTask() {
        String taskId = randomNumeric(5);
        fakeTaskLifecycleService.assign(null, null, taskId, "test");

        assertThat(fakeTaskLifecycleService.getRecordedActions().get(taskId)).contains(ASSIGN_ACTION);
    }

    @Test
    public void shouldStartTask() {
        String taskId = randomNumeric(5);
        fakeTaskLifecycleService.start(null, null, taskId);

        assertThat(fakeTaskLifecycleService.getRecordedActions().get(taskId)).contains(START_ACTION);
    }

    @Test
    public void shouldPauseTask() {
        String taskId = randomNumeric(5);
        fakeTaskLifecycleService.pause(null, null, taskId);

        assertThat(fakeTaskLifecycleService.getRecordedActions().get(taskId)).contains(PAUSE_ACTION);
    }

    @Test
    public void shouldResumeTask() {
        String taskId = randomNumeric(5);
        fakeTaskLifecycleService.resume(null, null, taskId);

        assertThat(fakeTaskLifecycleService.getRecordedActions().get(taskId)).contains(RESUME_ACTION);
    }

    @Test
    public void shouldCompleteTask() {
        String taskId = randomNumeric(5);
        fakeTaskLifecycleService.complete(null, null, taskId);

        assertThat(fakeTaskLifecycleService.getRecordedActions().get(taskId)).contains(COMPLETE_ACTION);
    }
}
