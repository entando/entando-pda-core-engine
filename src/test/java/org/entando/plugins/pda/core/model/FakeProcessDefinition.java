package org.entando.plugins.pda.core.model;

import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Singular;

public class FakeProcessDefinition extends ProcessDefinition {

    @Builder
    public FakeProcessDefinition(@Singular("extraProperty") Map<String,Object> extraProperties) {
        super(new HashMap<>(extraProperties));
    }

}
