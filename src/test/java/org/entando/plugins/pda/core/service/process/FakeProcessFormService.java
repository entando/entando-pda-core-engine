package org.entando.plugins.pda.core.service.process;

import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_ID_2;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_2;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_3;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_4;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_DESCRIPTION_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_DESCRIPTION_2;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_DESCRIPTION_3;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_DESCRIPTION_4;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_KEY_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_KEY_2;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_KEY_3;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_KEY_4;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_TYPE_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_TYPE_2;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_NAME_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_NAME_2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.ProcessNotFoundException;
import org.entando.plugins.pda.core.model.form.Form;
import org.entando.plugins.pda.core.model.form.FormField;
import org.entando.plugins.pda.core.model.form.FormFieldNumber;
import org.entando.plugins.pda.core.model.form.FormFieldSubForm;
import org.entando.plugins.pda.core.model.form.FormFieldText;
import org.entando.plugins.pda.core.model.form.FormFieldType;
import org.springframework.stereotype.Service;

@SuppressWarnings("PMD.ExcessiveImports")
@Service
public class FakeProcessFormService implements ProcessFormService {

    public static final Form PROCESS_FORM_1;

    static {
        FormField formField11 = FormFieldNumber.builder()
                .id(PROCESS_FORM_PROP_KEY_1)
                .type(FormFieldType.INTEGER)
                .name(PROCESS_FORM_PROP_KEY_1)
                .label(PROCESS_FORM_PROP_1)
                .placeholder(PROCESS_FORM_PROP_DESCRIPTION_1)
                .minValue(0)
                .maxValue(10)
                .build();

        FormField formField12 = FormFieldText.builder()
                .id(PROCESS_FORM_PROP_KEY_2)
                .type(FormFieldType.STRING)
                .name(PROCESS_FORM_PROP_KEY_2)
                .label(PROCESS_FORM_PROP_2)
                .placeholder(PROCESS_FORM_PROP_DESCRIPTION_2)
                .minLength(0)
                .maxLength(20)
                .build();

        FormFieldSubForm formField13 = FormFieldSubForm.builder()
                .id(PROCESS_FORM_PROP_KEY_3)
                .type(FormFieldType.SUBFORM)
                .name(PROCESS_FORM_PROP_KEY_3)
                .label(PROCESS_FORM_PROP_3)
                .placeholder(PROCESS_FORM_PROP_DESCRIPTION_3)
                .formId(PROCESS_FORM_ID_2)
                .formType(PROCESS_FORM_TYPE_2)
                .build();

        FormField formField21 = FormField.builder()
                .id(PROCESS_FORM_PROP_KEY_4)
                .type(FormFieldType.BOOLEAN)
                .name(PROCESS_FORM_PROP_KEY_4)
                .label(PROCESS_FORM_PROP_4)
                .placeholder(PROCESS_FORM_PROP_DESCRIPTION_4)
                .build();

        PROCESS_FORM_1 = Form.builder()
                .id(PROCESS_FORM_ID_1)
                .name(PROCESS_NAME_1)
                .type(PROCESS_FORM_TYPE_1)
                .fields(Arrays.asList(formField11, formField12, formField13))
                .build();

        Form subForm = Form.builder()
                .id(PROCESS_FORM_ID_2)
                .name(PROCESS_NAME_2)
                .type(PROCESS_FORM_TYPE_2)
                .fields(Collections.singletonList(formField21))
                .build();

        formField13.setForm(subForm);
    }

    @Override
    public Form get(Connection connection, String processId) {
        if (PROCESS_ID_1.equals(processId)){
            return PROCESS_FORM_1;
        }

        throw new ProcessNotFoundException();
    }

    @Override
    public String submit(Connection connection, String processDefinitionId, Map<String, Object> request) {
        if (PROCESS_ID_1.equals(processDefinitionId)){
            return UUID.randomUUID().toString();
        }

        throw new ProcessNotFoundException();
    }

}
