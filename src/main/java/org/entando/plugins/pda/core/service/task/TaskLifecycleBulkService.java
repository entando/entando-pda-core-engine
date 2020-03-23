package org.entando.plugins.pda.core.service.task;

import java.util.List;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.service.task.response.TaskBulkActionResponse;

/**
 * Defines methods for bulk lifecycle operations. Like the {@link TaskLifecycleService}, methods here move the task from
 * one state to another, but this interface works with multiple tasks at a time.
 *
 * <p>Implementations should use the {@link Connection} argument to get access to the engine.
 *
 * @see TaskLifecycleService
 * @see org.entando.plugins.pda.core.engine.Engine
 */
public interface TaskLifecycleBulkService {

    /**
     * Assign all tasks to the authenticated user.
     *
     * @param connection the connection to the BPM engine
     * @param user the authenticated user
     * @param ids the task ids
     * @return response with the status for each task id
     */
    List<TaskBulkActionResponse> bulkClaim(Connection connection, AuthenticatedUser user, List<String> ids);

    /**
     * Release all tasks that are currently assign to the authenticated user.
     *
     * @param connection the connection to the BPM engine
     * @param user the authenticated user
     * @param ids the task ids
     * @return response with the status for each task id
     */
    List<TaskBulkActionResponse> bulkUnclaim(Connection connection, AuthenticatedUser user, List<String> ids);

    /**
     * Delegate the tasks to another user.
     *
     * @param connection the connection to the BPM engine
     * @param user the authenticated user
     * @param ids the task ids
     * @param assignee the user to delegate the tasks to
     * @return response with the status for each task id
     */
    List<TaskBulkActionResponse> bulkAssign(Connection connection, AuthenticatedUser user, List<String> ids,
            String assignee);

    /**
     * Start the tasks to indicate they are being worked on.
     *
     * @param connection the connection to the BPM engine
     * @param user the authenticated user
     * @param ids the task ids
     * @return response with the status for each task id
     */
    List<TaskBulkActionResponse> bulkStart(Connection connection, AuthenticatedUser user, List<String> ids);

    /**
     * Pause the tasks to indicate they are not being worked on.
     *
     * @param connection the connection to the BPM engine
     * @param user the authenticated user
     * @param ids the task ids
     * @return response with the status for each task id
     */
    List<TaskBulkActionResponse> bulkPause(Connection connection, AuthenticatedUser user, List<String> ids);

    /**
     * Resume the tasks that were previously paused.
     *
     * @param connection the connection to the BPM engine
     * @param user the authenticated user
     * @param ids the task ids
     * @return response with the status for each task id
     */
    List<TaskBulkActionResponse> bulkResume(Connection connection, AuthenticatedUser user, List<String> ids);

    /**
     * Complete the tasks.
     *
     * @param connection the connection to the BPM engine
     * @param user the authenticated user
     * @param ids the task ids
     * @return response with the status for each task id
     */
    List<TaskBulkActionResponse> bulkComplete(Connection connection, AuthenticatedUser user, List<String> ids);

}
