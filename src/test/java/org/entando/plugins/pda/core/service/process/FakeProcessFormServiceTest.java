package org.entando.plugins.pda.core.service.process;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.entando.plugins.pda.core.service.process.FakeProcessFormService.PROCESS_FORM_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.randomStringId;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
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
        Form result = processFormService
                .get(Connection.builder().build(), PROCESS_ID_1);

        assertThat(result).isEqualTo(PROCESS_FORM_1);
    }

    @Test
    public void shouldThrowProcessNotFoundWhenGetProcessForm() {
        expectedException.expect(ProcessNotFoundException.class);
        processFormService.get(Connection.builder().build(), UUID.randomUUID().toString());
    }

    @Test
    public void shouldSubmitProcessForm() {
        String result = processFormService
                .submit(Connection.builder().build(), PROCESS_ID_1, new ConcurrentHashMap<>());

        assertThat(result).isNotEmpty();
    }

    @Test
    public void shouldThrowProcessNotFoundWhenSubmitProcessForm() {
        expectedException.expect(ProcessNotFoundException.class);
        processFormService.submit(Connection.builder().build(), randomStringId(), new ConcurrentHashMap<>());
    }
}
