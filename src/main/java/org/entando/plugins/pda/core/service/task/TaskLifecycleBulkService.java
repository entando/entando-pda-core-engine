package org.entando.plugins.pda.core.service.task;

import java.util.List;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.service.task.response.TaskBulkActionResponse;

public interface TaskLifecycleBulkService {

    List<TaskBulkActionResponse> bulkClaim(Connection connection, AuthenticatedUser user, List<String> ids);

    List<TaskBulkActionResponse> bulkUnclaim(Connection connection, AuthenticatedUser user, List<String> ids);

    List<TaskBulkActionResponse> bulkAssign(Connection connection, AuthenticatedUser user, List<String> ids,
            String assignee);

    List<TaskBulkActionResponse> bulkStart(Connection connection, AuthenticatedUser user, List<String> ids);

    List<TaskBulkActionResponse> bulkPause(Connection connection, AuthenticatedUser user, List<String> ids);

    List<TaskBulkActionResponse> bulkComplete(Connection connection, AuthenticatedUser user, List<String> ids);

}
