package org.entando.plugins.pda.core.model.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@ToString(callSuper = true)
public class FormFieldText extends FormField {

    private Integer maxLength;
    private Integer minLength;
    private Integer rows;

}
