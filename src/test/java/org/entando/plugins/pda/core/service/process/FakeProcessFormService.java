package org.entando.plugins.pda.core.service.process;

import java.util.Arrays;
import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.form.Form;
import org.entando.plugins.pda.core.model.form.FormField;
import org.entando.plugins.pda.core.model.form.FormFieldType;
import org.springframework.stereotype.Service;

@Service
public class FakeProcessFormService implements ProcessFormService {

    public static final String PROCESS_ID_1 = "1";
    public static final String PROCESS_NAME_1 = "Process 1";

    public static final String PROCESS_ID_2 = "2";
    public static final String PROCESS_NAME_2 = "Process 2";

    @Override
    public List<Form> getProcessForm(Connection connection, String processId) {

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

        Form pf1 = Form.builder()
                .id(PROCESS_ID_1)
                .name(PROCESS_NAME_1)
                .fields(Arrays.asList(formField1))
                .build();

        Form pf2 = Form.builder()
                .id(PROCESS_ID_2)
                .name(PROCESS_NAME_2)
                .fields(Arrays.asList(formField2))
                .build();

        return Arrays.asList(pf1, pf2);
    }

}
