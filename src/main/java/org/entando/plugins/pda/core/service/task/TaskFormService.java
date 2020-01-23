package org.entando.plugins.pda.core.service.task;

import java.util.Map;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.form.Form;

public interface TaskFormService {

    Form get(Connection connection, String taskId);

    String submit(Connection connection, AuthenticatedUser user, String taskId,
            Map<String, Object> request);

}
