package org.entando.plugins.pda.core.service.task;

import java.util.List;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.Task;
import org.entando.web.request.PagedListRequest;
import org.entando.web.response.PagedRestResponse;

/**
 * Defines service methods for task retrieval from the BPM engine.
 *
 * <p>Implementations should use the {@link Connection} argument to get access to the engine.
 *
 * @see org.entando.plugins.pda.core.engine.Engine
 */
public interface TaskService {

    /**
     * List tasks that can be potentially assigned to the authenticated user.
     *
     * @param connection the connection to the BPM engine
     * @param user the authenticated user
     * @param restListRequest the pagination and sorting for the request
     * @param filter the filter that needs to be applied to the query
     * @param groups the groups that need to be added to the query
     * @return paginated task list
     */
    PagedRestResponse<Task> list(Connection connection, AuthenticatedUser user, PagedListRequest restListRequest,
            String filter, List<String> groups);

    /**
     * Get task by id.
     *
     * @param connection the connection to the BPM engine
     * @param user the authenticated user
     * @param id the task id
     * @return task retrieved using the id
     */
    Task get(Connection connection, AuthenticatedUser user, String id);

}
