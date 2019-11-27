package org.entando.plugins.pda.core.service.process;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
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

}
