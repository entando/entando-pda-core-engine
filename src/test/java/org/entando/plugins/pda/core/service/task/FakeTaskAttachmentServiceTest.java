package org.entando.plugins.pda.core.service.task;

import static org.assertj.core.api.Assertions.assertThat;
import static org.entando.plugins.pda.core.service.task.FakeTaskAttachmentService.TASK_ATTACHMENTS_1;
import static org.entando.plugins.pda.core.service.task.FakeTaskAttachmentService.TASK_ATTACHMENTS_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_ID_1_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_ID_1_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ATTACHMENT_ID_2_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_2;
import static org.entando.plugins.pda.core.utils.TestUtils.getDummyConnection;
import static org.entando.plugins.pda.core.utils.TestUtils.getDummyUser;
import static org.entando.plugins.pda.core.utils.TestUtils.readFromFile;

import java.util.Arrays;
import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.AttachmentNotFoundException;
import org.entando.plugins.pda.core.exception.TaskNotFoundException;
import org.entando.plugins.pda.core.model.Attachment;
import org.entando.plugins.pda.core.model.File;
import org.entando.plugins.pda.core.request.CreateAttachmentRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FakeTaskAttachmentServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private FakeTaskAttachmentService taskService;

    @Before
    public void init() {
        taskService = new FakeTaskAttachmentService();
    }

    @Test
    public void shouldListTaskAttachments() {
        List<Attachment> taskAttachments1 = taskService
                .list(Connection.builder().build(), getDummyUser(), TASK_ID_1);

        assertThat(taskAttachments1).containsAll(Arrays.asList(TASK_ATTACHMENTS_1));

        List<Attachment> taskAttachments2 = taskService
                .list(Connection.builder().build(), getDummyUser(), TASK_ID_2);

        assertThat(taskAttachments2).containsAll(Arrays.asList(TASK_ATTACHMENTS_2));
    }

    @Test
    public void listTaskAttachmentsShouldThrowNotFoundException() {
        expectedException.expect(TaskNotFoundException.class);

        taskService.list(Connection.builder().build(), getDummyUser(), "invalid");
    }

    @Test
    public void shouldGetTaskAttachment() {
        Attachment taskAttachment11 = taskService
                .get(Connection.builder().build(), getDummyUser(), TASK_ID_1, TASK_ATTACHMENT_ID_1_2);

        assertThat(taskAttachment11).isEqualTo(TASK_ATTACHMENTS_1[1]);

        Attachment taskAttachment21 = taskService
                .get(Connection.builder().build(), getDummyUser(), TASK_ID_2, TASK_ATTACHMENT_ID_2_1);

        assertThat(taskAttachment21).isEqualTo(TASK_ATTACHMENTS_2[0]);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenGetAttachmentNonExistentTask() {
        expectedException.expect(AttachmentNotFoundException.class);

        taskService.get(Connection.builder().build(), getDummyUser(), "invalid", TASK_ATTACHMENT_ID_1_1);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenGetAttachmentNonExistentAttachment() {
        expectedException.expect(AttachmentNotFoundException.class);

        taskService.get(Connection.builder().build(), getDummyUser(), TASK_ID_1, "invalid");
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenGetAttachmentFromWrongTask() {
        expectedException.expect(AttachmentNotFoundException.class);

        taskService.get(Connection.builder().build(), getDummyUser(), TASK_ID_2, TASK_ATTACHMENT_ID_1_1);
    }

    @Test
    public void shouldCreateTaskAttachment() {
        try {
            CreateAttachmentRequest request = CreateAttachmentRequest.builder()
                    .file(readFromFile("task_attachment_file.txt"))
                    .build();

            Attachment result = taskService
                    .create(Connection.builder().build(), getDummyUser(), TASK_ID_2, request);

            Attachment taskAttachment22 = taskService.get(getDummyConnection(), getDummyUser(), TASK_ID_2,
                    result.getId());

            assertThat(result).isEqualTo(taskAttachment22);
        } finally { //rollback changes
            FakeTaskAttachmentService.buildAttachments();
        }
    }

    @Test
    public void shouldDeleteTaskAttachment() {
        try {
            String taskAttachment11 = taskService
                    .delete(Connection.builder().build(), getDummyUser(), TASK_ID_1, TASK_ATTACHMENT_ID_1_1);

            assertThat(taskAttachment11).isEqualTo(TASK_ATTACHMENT_ID_1_1);

            expectedException.expect(AttachmentNotFoundException.class);

            taskService.get(Connection.builder().build(), getDummyUser(), TASK_ID_1, TASK_ATTACHMENT_ID_1_1);
        } finally { //rollback changes
            FakeTaskAttachmentService.buildAttachments();
        }
    }

    @Test
    public void shouldDownloadTaskAttachment() {
        Attachment attachment = taskService.get(getDummyConnection(), getDummyUser(), TASK_ID_1,
                TASK_ATTACHMENT_ID_1_1);
        File file = taskService.download(getDummyConnection(), getDummyUser(), TASK_ID_1, TASK_ATTACHMENT_ID_1_1);

        assertThat(file.getData().length).isEqualTo(attachment.getSize().intValue());
    }
}
