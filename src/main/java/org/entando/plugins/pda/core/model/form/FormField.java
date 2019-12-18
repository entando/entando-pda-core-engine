package org.entando.plugins.pda.core.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
public class FormField {

    private String id;
    private String name;
    private String label;
    private boolean required;
    private boolean readOnly;
    private FormFieldType type;
    private String placeholder;

}
