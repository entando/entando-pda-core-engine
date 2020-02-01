package org.entando.plugins.pda.core.service.task;

import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.Task;
import org.entando.web.request.PagedListRequest;
import org.entando.web.response.PagedRestResponse;

public interface TaskService {

    PagedRestResponse<Task> list(Connection connection, AuthenticatedUser user, PagedListRequest restListRequest,
            String filter);

    Task get(Connection connection, AuthenticatedUser user, String id);
}
