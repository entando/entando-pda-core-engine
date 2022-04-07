package org.entando.plugins.pda.core.service.task;

import static org.assertj.core.api.Assertions.assertThat;
import static org.entando.plugins.pda.core.service.task.FakeTaskCommentService.TASK_COMMENTS_1;
import static org.entando.plugins.pda.core.service.task.FakeTaskCommentService.TASK_COMMENTS_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_COMMENT_ID_1_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_COMMENT_ID_2_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_2;
import static org.entando.plugins.pda.core.utils.TestUtils.getDummyUser;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.CommentNotFoundException;
import org.entando.plugins.pda.core.exception.TaskNotFoundException;
import org.entando.plugins.pda.core.model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FakeTaskCommentServiceTest {

    private FakeTaskCommentService taskService;

    @BeforeEach
    public void init() {
        taskService = new FakeTaskCommentService();
    }

    @Test
    public void shouldListTaskComments() {
        List<Comment> taskComments1 = taskService
                .list(Connection.builder().build(), getDummyUser(), TASK_ID_1);

        assertThat(taskComments1).containsAll(Arrays.asList(TASK_COMMENTS_1));

        List<Comment> taskComments2 = taskService
                .list(Connection.builder().build(), getDummyUser(), TASK_ID_2);

        assertThat(taskComments2).containsAll(Arrays.asList(TASK_COMMENTS_2));
    }

    @Test
    public void listTaskCommentsShouldThrowNotFoundException() {
        assertThrows(TaskNotFoundException.class,
                () -> taskService.list(Connection.builder().build(), getDummyUser(), "invalid"));
    }

    @Test
    public void shouldGetTaskComment() {
        Comment taskComment11 = taskService
                .get(Connection.builder().build(), getDummyUser(), TASK_ID_1, TASK_COMMENT_ID_1_1);

        assertThat(taskComment11).isEqualTo(TASK_COMMENTS_1[0]);

        Comment taskComment21 = taskService
                .get(Connection.builder().build(), getDummyUser(), TASK_ID_2, TASK_COMMENT_ID_2_1);

        assertThat(taskComment21).isEqualTo(TASK_COMMENTS_2[0]);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenGetCommentNonExistent() {
        assertThrows(CommentNotFoundException.class,
                () -> taskService.get(Connection.builder().build(), getDummyUser(), "invalid", TASK_COMMENT_ID_1_1));
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenGetCommentFromWrongTask() {
        assertThrows(CommentNotFoundException.class,
                () -> taskService.get(Connection.builder().build(), getDummyUser(), TASK_ID_2, TASK_COMMENT_ID_1_1));
    }

    @Test
    public void shouldDeleteTaskComment() {
        String taskComment11 = taskService
                .delete(Connection.builder().build(), getDummyUser(), TASK_ID_1, TASK_COMMENT_ID_1_1);

        assertThat(taskComment11).isEqualTo(TASK_COMMENT_ID_1_1);

        assertThrows(CommentNotFoundException.class,
                () -> taskService.get(Connection.builder().build(), getDummyUser(), TASK_ID_1, TASK_COMMENT_ID_1_1));
    }
}
