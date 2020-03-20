package org.entando.plugins.pda.core.service.task;

import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.Task;

/**
 * This interfaces defines service methods related to the task lifecycle. The lifecycle operations move the task from one
 * state to another.
 * <p>
 * Implementations should use the {@link Connection} argument to get access to the engine.
 *
 * @see org.entando.plugins.pda.core.engine.Engine
 */
public interface TaskLifecycleService {

    /**
     * Assign the task to the authenticated user.
     *
     * @param connection Connection to the BPM engine
     * @param user Authenticated user
     * @param id task id
     * @return the claimed task
     */
    Task claim(Connection connection, AuthenticatedUser user, String id);

    /**
     * Release the task that is assigned to the authenticated user.
     *
     * @param connection Connection to the BPM engine
     * @param user Authenticated user
     * @param id task id
     * @return the released task
     */
    Task unclaim(Connection connection, AuthenticatedUser user, String id);

    /**
     * Delegate the task to another user.
     *
     * @param connection Connection to the BPM engine
     * @param user Authenticated user
     * @param id task id
     * @param assignee the user to delegate the task to
     * @return the delegated task
     */
    Task assign(Connection connection, AuthenticatedUser user, String id, String assignee);

    /**
     * Start the task to indicate it is being worked on.
     *
     * @param connection Connection to the BPM engine
     * @param user Authenticated user
     * @param id task id
     * @return the started task
     */
    Task start(Connection connection, AuthenticatedUser user, String id);

    /**
     * Pause the task to indicate it is not being worked on.
     *
     * @param connection Connection to the BPM engine
     * @param user Authenticated user
     * @param id task id
     * @return the paused task
     */
    Task pause(Connection connection, AuthenticatedUser user, String id);

    /**
     * Resume the task that were previously paused.
     *
     * @param connection Connection to the BPM engine
     * @param user Authenticated user
     * @param id task id
     * @return the resumed task
     */
    Task resume(Connection connection, AuthenticatedUser user, String id);

    /**
     * Complete the task.
     *
     * @param connection Connection to the BPM engine
     * @param user Authenticated user
     * @param id task id
     * @return the completed task
     */
    Task complete(Connection connection, AuthenticatedUser user, String id);

}
