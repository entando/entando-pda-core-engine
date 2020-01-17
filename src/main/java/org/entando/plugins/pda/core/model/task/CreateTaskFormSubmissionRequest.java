package org.entando.plugins.pda.core.model.task;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskFormSubmissionRequest {

    @Singular
    @JsonAnySetter
    private Map<String, Map<String, Object>> forms = new ConcurrentHashMap<>();

}
