package org.entando.plugins.pda.core.service.process;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.entando.plugins.pda.core.utils.TestUtils.readFromFile;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.UUID;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.ProcessNotFoundException;
import org.entando.plugins.pda.core.model.ProcessDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FakeProcessServiceTest {

    private FakeProcessService processService;

    @BeforeEach
    public void init() {
        processService = new FakeProcessService();
    }

    @Test
    public void shouldListProcessesDefinitions() {
        List<ProcessDefinition> result = processService
                .listDefinitions(Connection.builder().build());

        assertThat(result).isNotEmpty();
    }

    @Test
    public void shouldGetProcessDiagram() {
        String result = processService.getProcessDiagram(Connection.builder().build(), FakeProcessService.PROCESS_ID_1);

        assertThat(result).isEqualTo(readFromFile(FakeProcessService.PROCESS_DIAGRAM_FILENAME_1));
    }

    @Test
    public void shouldThrowProcessNotFoundException() {
        assertThrows(ProcessNotFoundException.class,
                () -> processService.getProcessDiagram(Connection.builder().build(), UUID.randomUUID().toString()));
    }

}
