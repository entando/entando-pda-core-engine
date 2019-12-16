package org.entando.plugins.pda.core.service.task;

import java.util.Set;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;

public interface TaskDefinitionService {

    Set<String> listColumns(Connection connection, AuthenticatedUser user);

}
