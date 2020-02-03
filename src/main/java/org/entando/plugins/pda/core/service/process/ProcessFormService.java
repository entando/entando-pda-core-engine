package org.entando.plugins.pda.core.service.process;

import java.util.Map;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.form.Form;

public interface ProcessFormService {

    Form get(Connection connection, String processDefinitionId);

    String submit(Connection connection, String processDefinitionId, Map<String, Object> request);

}
