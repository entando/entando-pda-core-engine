package org.entando.plugins.pda.core.service.group;

import com.google.common.annotations.VisibleForTesting;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.entando.plugins.pda.core.engine.Connection;
import org.springframework.stereotype.Service;

@Service
public class FakeGroupService implements GroupService {

    private static final String SEPARATOR = "#";
    private final Map<String, List<String>> groupsMap = new ConcurrentHashMap<>();

    @Override
    public List<String> list(Connection connection, String containerId, String processId) {
        return groupsMap.get(containerId + SEPARATOR + processId);
    }

    @VisibleForTesting
    public void addGroups(String containerId, String processId, List<String> groups) {
        groupsMap.put(containerId + "#" + processId, groups);
    }
}
