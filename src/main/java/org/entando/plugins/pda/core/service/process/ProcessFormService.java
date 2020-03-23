package org.entando.plugins.pda.core.service.process;

import java.util.Map;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.form.Form;

/**
 * Defines service methods for process form operations, like retrieving the form definition and submitting
 * a form. The {@link Form} object can be used to render a form dynamically.
 * <p>
 * Implementations should use the {@link Connection} argument to get access to the engine.
 *
 * @see org.entando.plugins.pda.core.engine.Engine
 */
public interface ProcessFormService {

    /**
     * Return the current form associated to the process definition id.
     *
     * @param connection the connection to the BPM engine
     * @param processDefinitionId the process definition id
     * @return the form object
     */
    Form get(Connection connection, String processDefinitionId);

    /**
     * Submit the form to start a new process for the given process definition id.
     *
     * @param connection the connection to the BPM engine
     * @param processDefinitionId the process definition id
     * @param request the variables to fill out the form
     * @return process instance id
     */
    String submit(Connection connection, String processDefinitionId, Map<String, Object> request);

}
