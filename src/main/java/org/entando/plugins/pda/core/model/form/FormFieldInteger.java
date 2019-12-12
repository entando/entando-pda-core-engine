package org.entando.plugins.pda.core.model.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class FormFieldInteger extends FormField {

    private Integer minValue;
    private Integer maxValue;

}
