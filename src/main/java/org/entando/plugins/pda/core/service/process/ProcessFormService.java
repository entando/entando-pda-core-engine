package org.entando.plugins.pda.core.service.process;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.form.Form;

public interface ProcessFormService {
    List<Form> getProcessForm(Connection connection, String processId);
}
