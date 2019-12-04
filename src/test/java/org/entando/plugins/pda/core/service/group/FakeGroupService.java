package org.entando.plugins.pda.core.service.group;

import com.google.common.annotations.VisibleForTesting;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.entando.plugins.pda.core.engine.Connection;
import org.springframework.stereotype.Service;

@Service
public class FakeGroupService implements GroupService {

    private final Map<String, List<String>> groupsMap = new ConcurrentHashMap<>();

    @Override
    public List<String> list(Connection connection, String processId) {
        if (processId == null) {
            return groupsMap.values().stream().flatMap(List::stream).collect(Collectors.toList());
        }
        return groupsMap.get(processId);
    }

    @VisibleForTesting
    public void addGroups(String processId, List<String> groups) {
        groupsMap.put(processId, groups);
    }
}
