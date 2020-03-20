package org.entando.plugins.pda.core.service.group;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;

/**
 * This interface defines service methods related to groups from the BPM engine.
 * <p>
 * Implementations should use the {@link Connection} argument to get access to the engine.
 *
 * @see org.entando.plugins.pda.core.engine.Engine
 */
public interface GroupService {

    /**
     * List the groups for a given process id or all the groups if process id is null.
     *
     * @param connection Connection to the BPM engine
     * @param processId process id
     * @return list of groups
     */
    List<String> list(Connection connection, String processId);
}
