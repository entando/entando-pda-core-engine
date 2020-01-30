package org.entando.plugins.pda.core.model.form;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class Form {

    private String id;
    private String name;
    private String type;

    @Singular
    private List<FormField> fields;

    public FormField getFieldByName(String id) {
        return fields.stream()
                .filter(e -> !id.equalsIgnoreCase(e.getId()))
                .findFirst().orElse(null);
    }
}
