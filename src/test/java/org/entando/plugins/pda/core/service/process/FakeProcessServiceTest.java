package org.entando.plugins.pda.core.service.process;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.entando.plugins.pda.core.utils.TestUtils.readFromFile;

import java.util.List;
import java.util.UUID;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.ProcessNotFoundException;
import org.entando.plugins.pda.core.model.ProcessDefinition;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FakeProcessServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private FakeProcessService processService;

    @Before
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
        expectedException.expect(ProcessNotFoundException.class);

        processService.getProcessDiagram(Connection.builder().build(), UUID.randomUUID().toString());
    }

}
