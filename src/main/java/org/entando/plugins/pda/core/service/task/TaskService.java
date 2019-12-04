package org.entando.plugins.pda.core.service.task;

import java.util.Set;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.Task;
import org.entando.web.request.PagedListRequest;
import org.entando.web.response.PagedRestResponse;
import org.entando.web.response.SimpleRestResponse;

public interface TaskService {

    PagedRestResponse<Task> list(Connection connection, AuthenticatedUser user, PagedListRequest restListRequest);

    Task get(Connection connection, AuthenticatedUser user, String id);

    SimpleRestResponse<Set<String>> listTaskColumns(Connection connection, AuthenticatedUser user);
}
