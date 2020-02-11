package org.entando.plugins.pda.core.model.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@ToString(callSuper = true)
public class FormFieldDate extends FormField {

    private boolean withTime;

}
