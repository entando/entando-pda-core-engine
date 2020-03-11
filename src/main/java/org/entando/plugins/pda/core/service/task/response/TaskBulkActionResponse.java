package org.entando.plugins.pda.core.service.task.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskBulkActionResponse {

    private String id;
    private int statusCode;
}
