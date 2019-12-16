package org.entando.plugins.pda.core.service.process;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.ProcessNotFoundException;
import org.entando.plugins.pda.core.model.FakeProcessDefinition;
import org.entando.plugins.pda.core.model.ProcessDefinition;
import org.entando.plugins.pda.core.model.form.Form;
import org.entando.plugins.pda.core.model.form.FormField;
import org.entando.plugins.pda.core.model.form.FormFieldType;
import org.entando.web.exception.InternalServerException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class FakeProcessService implements ProcessService {

    public static final String PROCESS_ID_KEY = "id";
    public static final String PROCESS_NAME_KEY = "name";
    public static final String PROCESS_PROP_KEY = "new-key";

    public static final String PROCESS_DEFINITION_ID_1 = "process-1";
    public static final String PROCESS_ID_1 = "1";
    public static final String PROCESS_NAME_1 = "Process 1";
    public static final String PROCESS_PROP_1 = "New Prop 1";
    public static final String PROCESS_DIAGRAM_FILENAME_1 = "process_diagram_1.svg";
    public static final int PROCESS_DIAGRAM_LENGTH_1 = 63_945;

    public static final String PROCESS_DEFINITION_ID_2 = "process-2";
    public static final String PROCESS_ID_2 = "2";
    public static final String PROCESS_NAME_2 = "Process 2";
    public static final String PROCESS_PROP_2 = "New Prop 2";

    @Override
    public List<ProcessDefinition> listDefinitions(Connection connection) {
        return createProcessDefinitions();
    }

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

    @Override
    public String getProcessDiagram(Connection connection, String id) {
        if (PROCESS_ID_1.equals(id)) {
            return readFromFile(PROCESS_DIAGRAM_FILENAME_1);
        }

        throw new ProcessNotFoundException();
    }

    private String readFromFile(String filename) {
        try (InputStream is = new ClassPathResource(filename).getInputStream()){
             return IOUtils.toString(is);
        } catch (IOException e) {
            throw new InternalServerException("Error reading file", e);
        }
    }

    private List<ProcessDefinition> createProcessDefinitions() {
        List<ProcessDefinition> result = new ArrayList<>();

        result.add(FakeProcessDefinition.builder()
                .extraProperty(PROCESS_ID_KEY, PROCESS_DEFINITION_ID_1)
                .extraProperty(PROCESS_NAME_KEY, PROCESS_NAME_1)
                .extraProperty(PROCESS_PROP_KEY, PROCESS_PROP_1)
                .build());

        result.add(FakeProcessDefinition.builder()
                .extraProperty(PROCESS_ID_KEY, PROCESS_DEFINITION_ID_2)
                .extraProperty(PROCESS_NAME_KEY, PROCESS_NAME_2)
                .extraProperty(PROCESS_PROP_KEY, PROCESS_PROP_2)
                .build());

        return result;
    }
}
