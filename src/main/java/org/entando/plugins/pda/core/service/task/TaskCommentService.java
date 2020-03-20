package org.entando.plugins.pda.core.service.task;

import java.util.List;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.Comment;
import org.entando.plugins.pda.core.service.task.request.CreateCommentRequest;

/**
 * This interface defines service methods related to task comment manipulation. It should be implemented if task comment
 * is supported by the engine.
 * <p>
 * Implementations should use the {@link Connection} argument to get access to the engine.
 *
 * @see org.entando.plugins.pda.core.engine.Engine
 */
public interface TaskCommentService {

    /**
     * List all comments related to a task id.
     *
     * @param connection Connection to the BPM engine
     * @param user Authenticated user
     * @param id task id
     * @return the list of task comments
     */
    List<Comment> list(Connection connection, AuthenticatedUser user, String id);

    /**
     * Get task comment by task id and comment id.
     *
     * @param connection Connection to the BPM engine
     * @param user Authenticated user
     * @param id task id
     * @param commentId task comment id
     * @return the comment for the specified id
     */
    Comment get(Connection connection, AuthenticatedUser user, String id, String commentId);

    /**
     * Create a task comment for the specified task id.
     *
     * @param connection Connection to the BPM engine
     * @param user Authenticated user
     * @param id task id
     * @param comment Comment details to be created
     * @return created comment
     */
    Comment create(Connection connection, AuthenticatedUser user, String id, CreateCommentRequest comment);

    /**
     * Delete task comment by task id and comment id.
     *
     * @param connection Connection to the BPM engine
     * @param user Authenticated user
     * @param id task id
     * @param commentId task comment id
     * @return deleted commented id
     */
    String delete(Connection connection, AuthenticatedUser user, String id, String commentId);

}
