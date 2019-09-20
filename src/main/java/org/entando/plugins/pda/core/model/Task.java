package org.entando.plugins.pda.core.model;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {
    private String id;
    private String name;
    private String subject;
    private String description;
    private String status;
    private Integer priority;
    private Boolean skipable;
    private String processId;
    private String processInstanceId;
    private Map<String,String> properties;
}
