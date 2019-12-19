package org.entando.plugins.pda.core.service.process;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.entando.plugins.pda.core.service.process.FakeProcessFormService.PROCESS_FORMS;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_DEFINITION_ID;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_ID_1;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.ProcessNotFoundException;
import org.entando.plugins.pda.core.model.form.Form;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FakeProcessFormServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private FakeProcessFormService processFormService;

    @Before
    public void init() {
        processFormService = new FakeProcessFormService();
    }

    @Test
    public void shouldGetProcessForm() {
        List<Form> result = processFormService
                .getProcessForm(Connection.builder().build(), PROCESS_ID_1);

        assertThat(result).isEqualTo(PROCESS_FORMS);
    }

    @Test
    public void shouldThrowProcessNotFound() {
        expectedException.expect(ProcessNotFoundException.class);
        processFormService.getProcessForm(Connection.builder().build(), PROCESS_DEFINITION_ID);
    }

}
