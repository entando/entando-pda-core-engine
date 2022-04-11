package org.entando.plugins.pda.core.service.task;

import static org.assertj.core.groups.Tuple.tuple;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.entando.plugins.pda.core.service.task.response.TaskBulkActionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class FakeTaskLifecycleBulkServiceTest {

    private FakeTaskLifecycleBulkService taskLifecycleBulkService;

    private final List<String> ids = Arrays.asList("1", "2", "3");

    @BeforeEach
    public void setUp() {
        taskLifecycleBulkService = new FakeTaskLifecycleBulkService();
    }

    @Test
    public void shouldBulkClaim() {
        List<TaskBulkActionResponse> response = taskLifecycleBulkService.bulkClaim(null, null, ids);

        assertResponse(response);
    }

    @Test
    public void shouldBulkUnclaim() {
        List<TaskBulkActionResponse> response = taskLifecycleBulkService.bulkUnclaim(null, null, ids);

        assertResponse(response);
    }

    @Test
    public void shouldBulkAssign() {
        List<TaskBulkActionResponse> response = taskLifecycleBulkService.bulkAssign(null, null, ids, "test");

        assertResponse(response);
    }

    @Test
    public void shouldBulkStart() {
        List<TaskBulkActionResponse> response = taskLifecycleBulkService.bulkStart(null, null, ids);

        assertResponse(response);
    }

    @Test
    public void shouldBulkPause() {
        List<TaskBulkActionResponse> response = taskLifecycleBulkService.bulkPause(null, null, ids);

        assertResponse(response);
    }

    @Test
    public void shouldBulkResume() {
        List<TaskBulkActionResponse> response = taskLifecycleBulkService.bulkResume(null, null, ids);

        assertResponse(response);
    }

    @Test
    public void shouldBulkComplete() {
        List<TaskBulkActionResponse> response = taskLifecycleBulkService.bulkComplete(null, null, ids);

        assertResponse(response);
    }

    private void assertResponse(List<TaskBulkActionResponse> response) {
        Assertions.assertThat(response).extracting(TaskBulkActionResponse::getId, TaskBulkActionResponse::getStatusCode)
                .containsExactlyInAnyOrder(
                        tuple(ids.get(0), HttpStatus.OK.value()),
                        tuple(ids.get(1), HttpStatus.OK.value()),
                        tuple(ids.get(2), HttpStatus.OK.value())
                );
    }
}
