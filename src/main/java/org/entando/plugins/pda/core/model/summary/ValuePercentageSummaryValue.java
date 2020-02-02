package org.entando.plugins.pda.core.model.summary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ValuePercentageSummaryValue implements SummaryValue {

    private String title;
    private String totalLabel;
    private String total;
    private String percentage;

}
