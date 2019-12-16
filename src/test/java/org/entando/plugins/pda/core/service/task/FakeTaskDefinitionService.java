package org.entando.plugins.pda.core.service.task;

import com.google.common.collect.Sets;
import java.util.Set;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.springframework.stereotype.Service;

@Service
public class FakeTaskDefinitionService implements TaskDefinitionService {
    public static final Set<String> TASK_COLUMNS = Sets.newHashSet("id", "name", "subject");

    @Override
    public Set<String> listColumns(Connection connection, AuthenticatedUser user) {
        return TASK_COLUMNS;
    }
}
