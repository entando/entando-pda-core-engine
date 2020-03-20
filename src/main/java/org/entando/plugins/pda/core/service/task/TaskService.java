package org.entando.plugins.pda.core.service.task;

import java.util.List;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.Task;
import org.entando.web.request.PagedListRequest;
import org.entando.web.response.PagedRestResponse;

/**
 * This interface defines service methods for task retrieval from the BPM engine.
 * <p>
 * Implementations should use the {@link Connection} argument to get access to the engine.
 *
 * @see org.entando.plugins.pda.core.engine.Engine
 */
public interface TaskService {

    /**
     * List tasks that can be potentially assigned to the authenticated user.
     *
     * @param connection Connection to the BPM engine
     * @param user Authenticated user
     * @param restListRequest pagination and sorting for the request
     * @param filter filter that needs to be applied to the query
     * @param groups groups that need to be added to the query
     * @return paginated task list
     */
    PagedRestResponse<Task> list(Connection connection, AuthenticatedUser user, PagedListRequest restListRequest,
            String filter, List<String> groups);

    /**
     * Get task by id.
     *
     * @param connection Connection to the BPM engine
     * @param user Authenticated user
     * @param id task id
     * @return task retrieved using the id
     */
    Task get(Connection connection, AuthenticatedUser user, String id);

}
