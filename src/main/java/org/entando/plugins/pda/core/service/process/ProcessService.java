package org.entando.plugins.pda.core.service.process;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.ProcessDefinition;

/**
 * Defines service methods for process definitions operations.
 * <p>
 * Implementations should use the {@link Connection} argument to get access to the engine.
 *
 * @see org.entando.plugins.pda.core.engine.Engine
 */
public interface ProcessService {

    /**
     * List all process definitions for the given connection.
     *
     * @param connection the connection to the BPM engine
     * @return list with the process definitions
     */
    List<ProcessDefinition> listDefinitions(Connection connection);

    /**
     * Gets the process diagram for the given process id.
     *
     * @param connection the connection to the BPM engine
     * @param id the process id
     * @return list with the process definitions
     */
    String getProcessDiagram(Connection connection, String id);
}
