package org.entando.plugins.pda.core.service.task;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.ProcessDefinition;
import org.entando.plugins.pda.core.model.form.Form;

public interface ProcessService {

    List<ProcessDefinition> listDefinitions(Connection connection);

    List<Form> getProcessForm(Connection connection, String processId);

    String getProcessDiagram(Connection connection, String id);
}
