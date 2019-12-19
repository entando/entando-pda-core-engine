package org.entando.plugins.pda.core.service.task;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.form.Form;

public interface TaskFormService {
    List<Form> getTaskForm(Connection connection, String taskId);
}
