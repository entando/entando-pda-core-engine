package org.entando.plugins.pda.core.service.task;

import java.util.List;
import java.util.stream.Collectors;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.service.task.response.TaskBulkActionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FakeTaskLifecycleBulkService implements TaskLifecycleBulkService {

    @Override
    public List<TaskBulkActionResponse> bulkClaim(Connection connection, AuthenticatedUser user, List<String> ids) {
        return ids.stream()
                .map(e -> TaskBulkActionResponse.builder().id(e).statusCode(HttpStatus.OK.value()).build())
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskBulkActionResponse> bulkUnclaim(Connection connection, AuthenticatedUser user, List<String> ids) {
        return ids.stream()
                .map(e -> TaskBulkActionResponse.builder().id(e).statusCode(HttpStatus.OK.value()).build())
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskBulkActionResponse> bulkAssign(Connection connection, AuthenticatedUser user, List<String> ids,
            String assignee) {
        return ids.stream()
                .map(e -> TaskBulkActionResponse.builder().id(e).statusCode(HttpStatus.OK.value()).build())
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskBulkActionResponse> bulkStart(Connection connection, AuthenticatedUser user, List<String> ids) {
        return ids.stream()
                .map(e -> TaskBulkActionResponse.builder().id(e).statusCode(HttpStatus.OK.value()).build())
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskBulkActionResponse> bulkPause(Connection connection, AuthenticatedUser user, List<String> ids) {
        return ids.stream()
                .map(e -> TaskBulkActionResponse.builder().id(e).statusCode(HttpStatus.OK.value()).build())
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskBulkActionResponse> bulkComplete(Connection connection, AuthenticatedUser user, List<String> ids) {
        return ids.stream()
                .map(e -> TaskBulkActionResponse.builder().id(e).statusCode(HttpStatus.OK.value()).build())
                .collect(Collectors.toList());
    }
}
