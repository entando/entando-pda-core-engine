package org.entando.plugins.pda.core.service.task;

import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_ID_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_3;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_4;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_DESCRIPTION_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_DESCRIPTION_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_DESCRIPTION_3;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_DESCRIPTION_4;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_KEY_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_KEY_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_KEY_3;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_KEY_4;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_TYPE_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_TYPE_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_NAME_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_NAME_2;
import static org.entando.plugins.pda.core.utils.TestUtils.createFullTaskForm;
import static org.entando.plugins.pda.core.utils.TestUtils.createSimpleTaskForm;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.TaskNotFoundException;
import org.entando.plugins.pda.core.model.Task;
import org.entando.plugins.pda.core.model.form.Form;
import org.entando.plugins.pda.core.model.form.FormField;
import org.entando.plugins.pda.core.model.form.FormFieldNumber;
import org.entando.plugins.pda.core.model.form.FormFieldSubForm;
import org.entando.plugins.pda.core.model.form.FormFieldText;
import org.entando.plugins.pda.core.model.form.FormFieldType;
import org.springframework.stereotype.Service;

@SuppressWarnings("PMD.ExcessiveImports")
@Service
@RequiredArgsConstructor
public class FakeTaskFormService implements TaskFormService {

    private final FakeTaskService taskService;

    public static final Form TASK_FORM_1 = createSimpleTaskForm();
    public static final Form TASK_FORM_2 = createFullTaskForm();

    @Override
    public Form get(Connection connection, String taskId) {
        if (TASK_ID_1.equals(taskId)){
            return TASK_FORM_1;
        } else if (TASK_ID_2.equals(taskId)){
            return TASK_FORM_2;
        }

        throw new TaskNotFoundException();
    }

    @Override
    public String submit(Connection connection, AuthenticatedUser user, String taskId,
            Map<String, Object> request) {

        Task task = taskService.get(connection, null, taskId);

        task.setOutputData(new ConcurrentHashMap<>(request));
        return taskId;
    }

}
