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

    public static final Form TASK_FORM_1;

    static {
        FormField formField11 = FormFieldNumber.builder()
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

        FormFieldSubForm formField13 = FormFieldSubForm.builder()
                .id(TASK_FORM_PROP_3)
                .type(FormFieldType.SUBFORM)
                .name(TASK_FORM_PROP_KEY_3)
                .label(TASK_FORM_PROP_3)
                .placeholder(TASK_FORM_PROP_DESCRIPTION_3)
                .formId(TASK_FORM_ID_2)
                .formType(TASK_FORM_TYPE_2)
                .build();

        FormField formField21 = FormFieldText.builder()
                .id(TASK_FORM_PROP_4)
                .type(FormFieldType.STRING)
                .name(TASK_FORM_PROP_KEY_4)
                .label(TASK_FORM_PROP_4)
                .placeholder(TASK_FORM_PROP_DESCRIPTION_4)
                .build();

        TASK_FORM_1 = Form.builder()
                .id(TASK_FORM_ID_1)
                .name(TASK_NAME_1)
                .type(TASK_FORM_TYPE_1)
                .fields(Arrays.asList(formField11, formField12, formField13))
                .build();

        Form subForm = Form.builder()
                .id(TASK_ID_2)
                .name(TASK_NAME_2)
                .fields(Collections.singletonList(formField21))
                .build();

        formField13.setForm(subForm);
    }

    @Override
    public Form get(Connection connection, String taskId) {
        if (TASK_ID_1.equals(taskId)){
            return TASK_FORM_1;
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
