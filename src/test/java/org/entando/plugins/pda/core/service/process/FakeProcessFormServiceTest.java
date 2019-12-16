package org.entando.plugins.pda.core.service.process;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.form.Form;
import org.entando.plugins.pda.core.model.form.FormFieldType;
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

        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(2);

        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getName()).isEqualTo("Process 1");
        assertThat(result.get(0).getFields().size()).isEqualTo(1);
        assertThat(result.get(0).getFields().get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getFields().get(0).getType()).isEqualTo(FormFieldType.INTEGER);
        assertThat(result.get(0).getFields().get(0).getName()).isEqualTo("Process 1");
        assertThat(result.get(0).getFields().get(0).getLabel()).isEqualTo("Process 1");

        assertThat(result.get(1).getId()).isEqualTo("2");
        assertThat(result.get(1).getName()).isEqualTo("Process 2");
        assertThat(result.get(1).getFields().size()).isEqualTo(1);
        assertThat(result.get(1).getFields().get(0).getId()).isEqualTo("2");
        assertThat(result.get(1).getFields().get(0).getType()).isEqualTo(FormFieldType.INTEGER);
        assertThat(result.get(1).getFields().get(0).getName()).isEqualTo("Process 2");
        assertThat(result.get(1).getFields().get(0).getLabel()).isEqualTo("Process 2");

    }

}
