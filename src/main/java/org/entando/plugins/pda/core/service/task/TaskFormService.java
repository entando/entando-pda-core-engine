package org.entando.plugins.pda.core.service.task;

import java.util.List;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.Task;
import org.entando.plugins.pda.core.model.form.Form;
import org.entando.plugins.pda.core.model.task.CreateTaskFormSubmissionRequest;

public interface TaskFormService {

    List<Form> get(Connection connection, String taskId);

    Task submit(Connection connection, AuthenticatedUser user, String taskId, CreateTaskFormSubmissionRequest request);

}
