package org.entando.plugins.pda.core.service.task;

import java.util.List;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.Comment;
import org.entando.plugins.pda.core.service.task.request.CreateCommentRequest;

public interface TaskCommentService {

    List<Comment> list(Connection connection, AuthenticatedUser user, String id);

    Comment get(Connection connection, AuthenticatedUser user, String id, String commentId);

    Comment create(Connection connection, AuthenticatedUser user, String id, CreateCommentRequest comment);

    String delete(Connection connection, AuthenticatedUser user, String id, String commentId);

}
