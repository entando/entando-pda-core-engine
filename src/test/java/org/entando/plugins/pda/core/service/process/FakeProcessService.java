package org.entando.plugins.pda.core.service.process;

import static org.entando.plugins.pda.core.utils.TestUtils.readFromFile;

import java.util.ArrayList;
import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.ProcessNotFoundException;
import org.entando.plugins.pda.core.model.FakeProcessDefinition;
import org.entando.plugins.pda.core.model.ProcessDefinition;
import org.springframework.stereotype.Service;

@Service
public class FakeProcessService implements ProcessService {

    public static final String PROCESS_ID_KEY = "id";
    public static final String PROCESS_NAME_KEY = "name";
    public static final String PROCESS_PROP_KEY = "new-key";

    public static final String PROCESS_DEFINITION_ID_1 = "process-1";
    public static final String PROCESS_ID_1 = "process-1";
    public static final String PROCESS_NAME_1 = "Process 1";
    public static final String PROCESS_DIAGRAM_FILENAME_1 = "process_diagram_1.svg";
    public static final String PROCESS_DEFINITION_ID_2 = "process-2";
    public static final String PROCESS_NAME_2 = "Process 2";

    public static final String PROCESS_PROP_1 = "Age of Property";
    public static final String PROCESS_PROP_2 = "Address";

    @Override
    public List<ProcessDefinition> listDefinitions(Connection connection) {
        return createProcessDefinitions();
    }

    @Override
    public String getProcessDiagram(Connection connection, String id) {
        if (PROCESS_ID_1.equals(id)) {
            return readFromFile(PROCESS_DIAGRAM_FILENAME_1);
        }

        throw new ProcessNotFoundException();
    }

    private static List<ProcessDefinition> createProcessDefinitions() {
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
