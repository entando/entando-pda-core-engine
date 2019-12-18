package org.entando.plugins.pda.core.service.process;

import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_ID_2;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_NAME_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_NAME_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.ProcessNotFoundException;
import org.entando.plugins.pda.core.model.form.Form;
import org.entando.plugins.pda.core.model.form.FormField;
import org.entando.plugins.pda.core.model.form.FormFieldType;
import org.springframework.stereotype.Service;

@Service
public class FakeProcessFormService implements ProcessFormService {

    public static final List<Form> FORMS = new ArrayList<>();

    static {
        FormField formField1 = FormField.builder()
                .id(PROCESS_ID_1)
                .type(FormFieldType.INTEGER)
                .name(PROCESS_NAME_1)
                .label(PROCESS_NAME_1)
                .build();

        FormField formField2 = FormField.builder()
                .id(PROCESS_ID_2)
                .type(FormFieldType.INTEGER)
                .name(PROCESS_NAME_2)
                .label(PROCESS_NAME_2)
                .build();

        FORMS.add(Form.builder()
                .id(PROCESS_ID_1)
                .name(PROCESS_NAME_1)
                .fields(Arrays.asList(formField1))
                .build());

        FORMS.add(Form.builder()
                .id(PROCESS_ID_2)
                .name(PROCESS_NAME_2)
                .fields(Arrays.asList(formField2))
                .build());
    }

    @Override
    public List<Form> getProcessForm(Connection connection, String processId) {
        if (PROCESS_ID_1.equals(processId)){
            return FORMS;
        }

        throw new ProcessNotFoundException();
    }

}
