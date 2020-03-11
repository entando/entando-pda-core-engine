package org.entando.plugins.pda.core.service.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.Task;
import org.springframework.stereotype.Service;

@Service
public class FakeTaskLifecycleService implements TaskLifecycleService {

    public static final String CLAIM_ACTION = "claim";
    public static final String UNCLAIM_ACTION = "unclaim";
    public static final String ASSIGN_ACTION = "assign";
    public static final String START_ACTION = "start";
    public static final String PAUSE_ACTION = "pause";
    public static final String RESUME_ACTION = "resume";
    public static final String COMPLETE_ACTION = "complete";

    private final Map<String, List<String>> recordedActions = new ConcurrentHashMap<>();

    @Override
    public Task claim(Connection connection, AuthenticatedUser user, String id) {
        return recordAction(id, CLAIM_ACTION);
    }

    @Override
    public Task unclaim(Connection connection, AuthenticatedUser user, String id) {
        return recordAction(id, UNCLAIM_ACTION);
    }

    @Override
    public Task assign(Connection connection, AuthenticatedUser user, String id, String assignee) {
        return recordAction(id, ASSIGN_ACTION);
    }

    @Override
    public Task start(Connection connection, AuthenticatedUser user, String id) {
        return recordAction(id, START_ACTION);
    }

    @Override
    public Task pause(Connection connection, AuthenticatedUser user, String id) {
        return recordAction(id, PAUSE_ACTION);
    }

    @Override
    public Task resume(Connection connection, AuthenticatedUser user, String id) {
        return recordAction(id, RESUME_ACTION);
    }

    @Override
    public Task complete(Connection connection, AuthenticatedUser user, String id) {
        return recordAction(id, COMPLETE_ACTION);
    }

    public Map<String, List<String>> getRecordedActions() {
        return recordedActions;
    }

    private Task recordAction(String id, String complete) {
        List<String> actions = recordedActions.get(id);
        if (actions == null) {
            actions = new ArrayList<>();
        }
        actions.add(complete);
        recordedActions.put(id, actions);
        Task result = new Task();
        result.setId(id);
        return result;
    }
}
