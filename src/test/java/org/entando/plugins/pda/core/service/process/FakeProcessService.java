package org.entando.plugins.pda.core.service.process;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.ProcessNotFoundException;
import org.entando.plugins.pda.core.model.FakeProcessDefinition;
import org.entando.plugins.pda.core.model.ProcessDefinition;
import org.entando.plugins.pda.core.service.task.ProcessService;
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
    public static final int PROCESS_DIAGRAM_LENGTH_1 = 63_453;

    public static final String PROCESS_DEFINITION_ID_2 = "process-2";
    public static final String PROCESS_NAME_2 = "Process 2";
    public static final String PROCESS_PROP_2 = "New Prop 2";

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

    private String readFromFile(String filename) {
        InputStream is;
        try {
            File file = new ClassPathResource(filename).getFile();
            is = Files.newInputStream(Paths.get(file.getAbsolutePath()));
            return IOUtils.toString(is, Charset.defaultCharset().toString());
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
