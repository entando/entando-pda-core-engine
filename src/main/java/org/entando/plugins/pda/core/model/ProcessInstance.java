package org.entando.plugins.pda.core.model;

import java.time.LocalDateTime;
import java.util.List;
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
public class ProcessInstance {

    private String id;
    private String processDefinitionId;
    private String processName;
    private String processVersion;
    private String state;
    private String initiator;
    private LocalDateTime date;
    private List<String> userTasks;
}
