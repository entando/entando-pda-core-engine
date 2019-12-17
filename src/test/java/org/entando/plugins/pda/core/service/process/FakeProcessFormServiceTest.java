package org.entando.plugins.pda.core.service.process;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
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
                .getProcessForm(Connection.builder().build(), "processId01");

        assertThat(result).isEqualTo(FakeProcessFormService.FORMS);
    }

}
