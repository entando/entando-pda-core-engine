package org.entando.plugins.pda.core.model.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@ToString(callSuper = true)
public class FormFieldSubForm extends FormField {

    private String formId;
    private String formType;
    private Form form;

}
