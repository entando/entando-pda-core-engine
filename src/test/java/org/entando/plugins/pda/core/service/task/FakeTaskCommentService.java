package org.entando.plugins.pda.core.service.task;

import static org.entando.plugins.pda.core.utils.TestUtils.TASK_COMMENT_1_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_COMMENT_1_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_COMMENT_2_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_COMMENT_ID_1_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_COMMENT_ID_1_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_COMMENT_ID_2_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.CommentNotFoundException;
import org.entando.plugins.pda.core.exception.TaskNotFoundException;
import org.entando.plugins.pda.core.model.Comment;
import org.entando.plugins.pda.core.request.CreateCommentRequest;
import org.springframework.stereotype.Service;

@Service
public class FakeTaskCommentService implements TaskCommentService {

    public static final Comment[] TASK_COMMENTS_1 = new Comment[]{
            Comment.builder()
                    .id(TASK_COMMENT_ID_1_1)
                    .text(TASK_COMMENT_1_1)
                    .createdBy("admin")
                    .createdAt(new Date())
                    .build(),
            Comment.builder()
                    .id(TASK_COMMENT_ID_1_2)
                    .text(TASK_COMMENT_1_2)
                    .createdBy("jack bauer")
                    .createdAt(new Date())
                    .build()
    };

    public static final Comment[] TASK_COMMENTS_2 = new Comment[]{
            Comment.builder()
                    .id(TASK_COMMENT_ID_2_1)
                    .text(TASK_COMMENT_2_1)
                    .createdBy("chuck norris")
                    .createdAt(new Date())
                    .build()
    };

    private static final Map<String, List<Comment>> COMMENTS_MAP;

    static {
        COMMENTS_MAP = new ConcurrentHashMap<>();
        COMMENTS_MAP.put(TASK_ID_1, new ArrayList<>(Arrays.asList(TASK_COMMENTS_1)));
        COMMENTS_MAP.put(TASK_ID_2, new ArrayList<>(Arrays.asList(TASK_COMMENTS_2)));
    }

    @Override
    public List<Comment> list(Connection connection, AuthenticatedUser user, String id) {

        if(COMMENTS_MAP.containsKey(id)) {
            return COMMENTS_MAP.get(id);
        }

        throw new TaskNotFoundException();
    }

    @Override
    public Comment get(Connection connection, AuthenticatedUser user, String id, String commentId) {
        List<Comment> comments = Optional.ofNullable(COMMENTS_MAP.get(id))
                .orElseThrow(CommentNotFoundException::new);

        for (Comment comment : comments) {
            if (comment.getId().equals(commentId)) {
                return comment;
            }
        }

        throw new CommentNotFoundException();
    }

    @Override
    public Comment create(Connection connection, AuthenticatedUser user, String id, CreateCommentRequest request) {
        List<Comment> comments = COMMENTS_MAP.get(id);
        if (comments == null) {
            comments = new ArrayList<>();
        }

        Comment comment = Comment.builder()
                .id(UUID.randomUUID().toString())
                .text(request.getComment())
                .createdBy(user == null ? null : user.getUserId())
                .createdAt(new Date())
                .build();

        comments.add(comment);

        COMMENTS_MAP.put(id, comments);
        return comment;
    }

    @Override
    public String delete(Connection connection, AuthenticatedUser user, String id, String commentId) {
        Comment comment = get(connection, user, id, commentId);

        List<Comment> comments = COMMENTS_MAP.get(id);
        comments.remove(comment);

        COMMENTS_MAP.put(id, comments);

        return commentId;
    }
}
