package org.entando.plugins.pda.core.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.Map;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BaseMapModel {

    @JsonAnySetter
    @SuppressWarnings("PMD.UseConcurrentHashMap")
    protected Map<String, Object> data = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getData() {
        return data;
    }

    public BaseMapModel(Map<String, Object> map) {
        data = map == null ? new HashMap<>() : new HashMap<>(map);
    }

    public void putAll(Map<String, Object> map) {
        data.putAll(map);
    }
}
