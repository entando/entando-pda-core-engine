package org.entando.plugins.pda.core.service.process;

import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_ID_1;
import static org.entando.plugins.pda.core.utils.TestUtils.PROCESS_ID_2;
import static org.entando.plugins.pda.core.utils.TestUtils.createFullProcessForm;
import static org.entando.plugins.pda.core.utils.TestUtils.createSimpleProcessForm;

import java.util.Map;
import java.util.UUID;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.ProcessNotFoundException;
import org.entando.plugins.pda.core.model.form.Form;
import org.springframework.stereotype.Service;

@SuppressWarnings("PMD.ExcessiveImports")
@Service
public class FakeProcessFormService implements ProcessFormService {

    public static final Form PROCESS_FORM_1 = createSimpleProcessForm();
    public static final Form PROCESS_FORM_2 = createFullProcessForm();

    @Override
    public Form get(Connection connection, String processId) {
        if (PROCESS_ID_1.equals(processId)){
            return PROCESS_FORM_1;
        } else if (PROCESS_ID_2.equals(processId)) {
            return PROCESS_FORM_2;
        }

        throw new ProcessNotFoundException();
    }

    @Override
    public String submit(Connection connection, String processDefinitionId, Map<String, Object> request) {
        if (PROCESS_ID_1.equals(processDefinitionId) || PROCESS_ID_2.equals(processDefinitionId)){
            return UUID.randomUUID().toString();
        }

        throw new ProcessNotFoundException();
    }

}
