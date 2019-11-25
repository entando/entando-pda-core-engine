package org.entando.plugins.pda.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.entando.web.response.BaseMapModel;

@NoArgsConstructor
public abstract class Process extends BaseMapModel {

    public Process(Map<String,Object> map) {
        super(map);
    }

    @JsonIgnore
    public abstract String getId();

    @JsonIgnore
    public abstract String getName();

    @JsonIgnore
    public abstract String getContainerId();
}
