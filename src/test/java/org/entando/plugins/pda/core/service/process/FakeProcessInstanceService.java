package org.entando.plugins.pda.core.service.process;

import com.google.common.annotations.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.ProcessInstance;
import org.springframework.stereotype.Service;

@Service
public class FakeProcessInstanceService implements ProcessInstanceService {

    private Map<String, List<ProcessInstance>> processInstancesByInitiator;

    @Override
    public List<ProcessInstance> list(Connection connection, String processDefinitionId, AuthenticatedUser user) {
        return processInstancesByInitiator.get(processDefinitionId);
    }

    @VisibleForTesting
    public void initiateProcess(String processDefinitionId, ProcessInstance processInstance) {
        if (processInstancesByInitiator == null) {
            processInstancesByInitiator = new HashMap<>();
        }
        List<ProcessInstance> processInstances = processInstancesByInitiator
                .computeIfAbsent(processDefinitionId, k -> new ArrayList<>());
        processInstances.add(processInstance);
    }
}
