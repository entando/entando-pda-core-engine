package org.entando.plugins.pda.core.service.process;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.entando.plugins.pda.core.service.process.FakeProcessFormService.PROCESS_FORM_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.getDummyUser;
import static org.entando.plugins.pda.core.utils.TestUtils.randomStringId;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.ProcessNotFoundException;
import org.entando.plugins.pda.core.model.form.Form;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FakeProcessFormServiceTest {

    private FakeProcessFormService processFormService;

    @BeforeEach
    public void init() {
        processFormService = new FakeProcessFormService();
    }

    @Test
    public void shouldGetProcessForm() {
        Form result = processFormService.get(Connection.builder().build(), PROCESS_ID_1);

        assertThat(result).isEqualTo(PROCESS_FORM_1);
    }

    @Test
    public void shouldThrowProcessNotFoundWhenGetProcessForm() {
        assertThrows(ProcessNotFoundException.class,
                () -> processFormService.get(Connection.builder().build(), UUID.randomUUID().toString()));
    }

    @Test
    public void shouldSubmitProcessForm() {
        String result = processFormService
                .submit(Connection.builder().build(), PROCESS_ID_1, new ConcurrentHashMap<>(), getDummyUser());

        assertThat(result).isNotEmpty();
    }

    @Test
    public void shouldThrowProcessNotFoundWhenSubmitProcessForm() {
        assertThrows(ProcessNotFoundException.class, () -> processFormService
                .submit(Connection.builder().build(), randomStringId(), new ConcurrentHashMap<>(), getDummyUser()));
    }
}
