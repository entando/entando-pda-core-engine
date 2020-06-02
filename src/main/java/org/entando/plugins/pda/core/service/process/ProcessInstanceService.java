package org.entando.plugins.pda.core.service.process;

import java.util.List;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.ProcessInstance;

/**
 * Service class for operations related to process instances.
 *
 * <p>A process instance is created when the user initiates a new process based on a process definition.
 *
 * <p>Implementations should use the {@link Connection} argument to get access to the engine.
 *
 * @see ProcessService
 * @see org.entando.plugins.pda.core.engine.Engine
 */
public interface ProcessInstanceService {

    /**
     * List process instances by process definition and the user who initiated the process (initiator).
     *
     * @param user the user who initiated the process
     * @return list of process instances
     */
    List<ProcessInstance> list(Connection connection, String processDefinitionId, AuthenticatedUser user);
}
