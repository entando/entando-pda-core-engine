package org.entando.plugins.pda.core.service.task;

import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_3;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_DESCRIPTION_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_DESCRIPTION_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_DESCRIPTION_3;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_KEY_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_KEY_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_FORM_PROP_KEY_3;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_ID_2;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_NAME_1;
import static org.entando.plugins.pda.core.utils.TestUtils.TASK_NAME_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.TaskNotFoundException;
import org.entando.plugins.pda.core.model.Task;
import org.entando.plugins.pda.core.model.form.Form;
import org.entando.plugins.pda.core.model.form.FormField;
import org.entando.plugins.pda.core.model.form.FormFieldInteger;
import org.entando.plugins.pda.core.model.form.FormFieldText;
import org.entando.plugins.pda.core.model.form.FormFieldType;
import org.entando.plugins.pda.core.model.task.CreateTaskFormSubmissionRequest;
import org.springframework.stereotype.Service;

@SuppressWarnings("PMD.ExcessiveImports")
@Service
@RequiredArgsConstructor
public class FakeTaskFormService implements TaskFormService {

    private final FakeTaskService taskService;

    public static final List<Form> TASK_FORMS = new ArrayList<>();

    static {
        FormField formField11 = FormFieldInteger.builder()
                .id(TASK_FORM_PROP_1)
                .type(FormFieldType.INTEGER)
                .name(TASK_FORM_PROP_KEY_1)
                .label(TASK_FORM_PROP_1)
                .placeholder(TASK_FORM_PROP_DESCRIPTION_1)
                .minValue(0)
                .maxValue(10)
                .build();

        FormField formField12 = FormField.builder()
                .id(TASK_FORM_PROP_2)
                .type(FormFieldType.BOOLEAN)
                .name(TASK_FORM_PROP_KEY_2)
                .label(TASK_FORM_PROP_2)
                .placeholder(TASK_FORM_PROP_DESCRIPTION_2)
                .build();

        FormField formField21 = FormFieldText.builder()
                .id(TASK_FORM_PROP_3)
                .type(FormFieldType.STRING)
                .name(TASK_FORM_PROP_KEY_3)
                .label(TASK_FORM_PROP_3)
                .placeholder(TASK_FORM_PROP_DESCRIPTION_3)
                .build();

        Form pf1 = Form.builder()
                .id(TASK_ID_1)
                .name(TASK_NAME_1)
                .fields(Arrays.asList(formField11, formField12))
                .build();

        Form pf2 = Form.builder()
                .id(TASK_ID_2)
                .name(TASK_NAME_2)
                .fields(Collections.singletonList(formField21))
                .build();

        TASK_FORMS.add(pf1);
        TASK_FORMS.add(pf2);
    }

    @Override
    public List<Form> get(Connection connection, String taskId) {
        if (TASK_ID_1.equals(taskId)){
            return TASK_FORMS;
        }

        throw new TaskNotFoundException();
    }

    @Override
    public Task submit(Connection connection, AuthenticatedUser user, String taskId,
            CreateTaskFormSubmissionRequest request) {

        Task task = taskService.get(connection, null, taskId);

        Map<String, Object> variables = new ConcurrentHashMap<>();
        request.getForms().forEach((key, value) -> value.forEach(variables::put));

        task.setOutputData(variables);
        return task;
    }

}
