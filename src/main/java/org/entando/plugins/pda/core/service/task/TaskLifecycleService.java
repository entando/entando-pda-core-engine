package org.entando.plugins.pda.core.service.task;

import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.Task;

public interface TaskLifecycleService {

    Task claim(Connection connection, AuthenticatedUser user, String id);

    Task unclaim(Connection connection, AuthenticatedUser user, String id);

    Task assign(Connection connection, AuthenticatedUser user, String id, String assignee);

    Task start(Connection connection, AuthenticatedUser user, String id);

    Task pause(Connection connection, AuthenticatedUser user, String id);

    Task resume(Connection connection, AuthenticatedUser user, String id);

    Task complete(Connection connection, AuthenticatedUser user, String id);

}
