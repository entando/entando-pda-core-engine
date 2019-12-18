package org.entando.plugins.pda.core.model.form;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Form {

    private String id;
    private String name;
    private List<FormField> fields;
}
