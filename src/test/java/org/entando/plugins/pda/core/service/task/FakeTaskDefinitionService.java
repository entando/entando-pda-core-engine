package org.entando.plugins.pda.core.service.task;

import com.google.common.collect.Sets;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class FakeTaskDefinitionService implements TaskDefinitionService {
    public static final Set<String> TASK_COLUMNS = Sets.newHashSet("id", "name", "subject");

    @Override
    public Set<String> listColumns() {
        return TASK_COLUMNS;
    }
}
