package org.entando.plugins.pda.core.model.form;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@ToString(callSuper = true)
public class FormFieldSelector extends FormField {
    private List<Option> options;
    private String defaultValue;
    private boolean multiple;

    @Builder
    @Data
    public static class Option {
        private String value;
        private String label;
    }
}
