package org.entando.plugins.pda.core.model;

import lombok.Builder;
import lombok.Data;

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

    public void addProperty(String key, String value){
        if (properties == null) {
            properties = new HashMap<>();
        }

        properties.put(key,value);
    }
}
