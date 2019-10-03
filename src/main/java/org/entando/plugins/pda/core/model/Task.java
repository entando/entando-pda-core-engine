package org.entando.plugins.pda.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.entando.web.response.BaseMapModel;

@NoArgsConstructor
public abstract class Task extends BaseMapModel {

    public Task(Map<String,Object> map) {
        super(map);
    }

    @JsonIgnore
    public abstract String getId();

    @JsonIgnore
    public abstract String getName();

    @JsonIgnore
    public abstract String getProcessId();

    @JsonIgnore
    public abstract String getProcessInstanceId();

    @JsonIgnore
    public abstract String getContainerId();
}
