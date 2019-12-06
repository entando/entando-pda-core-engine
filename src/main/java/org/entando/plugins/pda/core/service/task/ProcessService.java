package org.entando.plugins.pda.core.service.task;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.ProcessDefinition;

public interface ProcessService {
    List<ProcessDefinition> listDefinitions(Connection connection);

    String getProcessDiagram(Connection connection, String id);
}
