package org.entando.plugins.pda.core.service.task;

import java.util.Set;

/**
 * Defines service methods related to task definition. A task definition specifies which fields or
 * columns are available for all task instance.
 *
 * @see org.entando.plugins.pda.core.engine.Engine
 */
public interface TaskDefinitionService {

    /**
     * List task columns/fields
     *
     * @return task column names
     */
    Set<String> listColumns();

}
