package org.entando.plugins.pda.core.service.group;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;

public interface GroupService {

    List<String> list(Connection connection, String containerId, String processId);
}
