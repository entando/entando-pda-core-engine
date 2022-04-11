package org.entando.plugins.pda.core.model;

import java.util.Map;
import lombok.NoArgsConstructor;
import org.entando.plugins.pda.core.response.BaseMapModel;

@NoArgsConstructor
public abstract class ProcessDefinition extends BaseMapModel {

    public ProcessDefinition(Map<String,Object> map) {
        super(map);
    }

}
