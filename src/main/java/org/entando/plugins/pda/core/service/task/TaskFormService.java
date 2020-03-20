package org.entando.plugins.pda.core.service.task;

import java.util.Map;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.form.Form;

/**
 * This interface defines service methods for task form operations, like retrieving the form definition and submitting
 * a form. The {@link Form} object can be used to render a form dynamically.
 * <p>
 * Implementations should use the {@link Connection} argument to get access to the engine.
 *
 * @see org.entando.plugins.pda.core.engine.Engine
 */
public interface TaskFormService {

    /**
     * Return the current form associated to the task id.
     *
     * @param connection Connection to the BPM engine
     * @param taskId task id
     * @return the form object
     */
    Form get(Connection connection, String taskId);

    /**
     * Submit the current form for the given task id.
     *
     * @param connection Connection to the BPM engine
     * @param user authenticated user
     * @param taskId task id
     * @param request variables to fill out the form
     * @return task id
     */
    String submit(Connection connection, AuthenticatedUser user, String taskId,
            Map<String, Object> request);

}
