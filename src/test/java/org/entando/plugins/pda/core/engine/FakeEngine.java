package org.entando.plugins.pda.core.engine;

import org.entando.plugins.pda.core.service.group.FakeGroupService;
import org.entando.plugins.pda.core.service.task.FakeTaskService;
import org.springframework.stereotype.Component;

@Component
public class FakeEngine extends Engine {

    public static final String TYPE = "fake";

    public FakeEngine(FakeTaskService taskService, FakeGroupService groupService) {
        super(TYPE, taskService, groupService);
    }
}
