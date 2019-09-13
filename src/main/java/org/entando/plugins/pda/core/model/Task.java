package org.entando.plugins.pda.core.model;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.HashMap;
import java.util.Map;

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
