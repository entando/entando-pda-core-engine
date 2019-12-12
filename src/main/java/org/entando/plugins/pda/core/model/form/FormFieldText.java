package org.entando.plugins.pda.core.model.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class FormFieldText extends FormField {

    private Integer maxLength;
    private Integer minLength;

}
