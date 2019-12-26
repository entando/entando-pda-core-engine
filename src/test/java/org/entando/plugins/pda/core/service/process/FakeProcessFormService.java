package org.entando.plugins.pda.core.service.process;

import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_2;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_3;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_DESCRIPTION_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_DESCRIPTION_2;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_DESCRIPTION_3;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_KEY_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_KEY_2;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_FORM_PROP_KEY_3;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_ID_2;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_NAME_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_NAME_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.ProcessNotFoundException;
import org.entando.plugins.pda.core.model.form.Form;
import org.entando.plugins.pda.core.model.form.FormField;
import org.entando.plugins.pda.core.model.form.FormFieldInteger;
import org.entando.plugins.pda.core.model.form.FormFieldText;
import org.entando.plugins.pda.core.model.form.FormFieldType;
import org.springframework.stereotype.Service;

@Service
public class FakeProcessFormService implements ProcessFormService {

    public static final List<Form> PROCESS_FORMS = new ArrayList<>();

    static {
        FormField formField11 = FormFieldInteger.builder()
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

        FormField formField21 = FormField.builder()
                .id(PROCESS_FORM_PROP_KEY_3)
                .type(FormFieldType.BOOLEAN)
                .name(PROCESS_FORM_PROP_KEY_3)
                .label(PROCESS_FORM_PROP_3)
                .placeholder(PROCESS_FORM_PROP_DESCRIPTION_3)
                .build();

        Form pf1 = Form.builder()
                .id(PROCESS_ID_1)
                .name(PROCESS_NAME_1)
                .fields(Arrays.asList(formField11, formField12))
                .build();

        Form pf2 = Form.builder()
                .id(PROCESS_ID_2)
                .name(PROCESS_NAME_2)
                .fields(Collections.singletonList(formField21))
                .build();

        PROCESS_FORMS.add(pf1);
        PROCESS_FORMS.add(pf2);
    }

    @Override
    public List<Form> getProcessForm(Connection connection, String processId) {
        if (PROCESS_ID_1.equals(processId)){
            return PROCESS_FORMS;
        }

        throw new ProcessNotFoundException();
    }

}
