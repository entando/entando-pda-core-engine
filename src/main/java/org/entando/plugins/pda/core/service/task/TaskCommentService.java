package org.entando.plugins.pda.core.service.task;

import java.util.List;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.Comment;
import org.entando.plugins.pda.core.request.CreateCommentRequest;

public interface TaskCommentService {

    List<Comment> listComments(Connection connection, AuthenticatedUser user, String id);

    Comment getComment(Connection connection, AuthenticatedUser user, String id, String commentId);

    Comment createComment(Connection connection, AuthenticatedUser user, String id, CreateCommentRequest comment);

    String deleteComment(Connection connection, AuthenticatedUser user, String id, String commentId);

}
