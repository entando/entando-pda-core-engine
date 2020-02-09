package org.entando.plugins.pda.core.service.summary.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entando.plugins.pda.core.model.summary.SummaryFrequency;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardSummaryRequest {
    private String frequency = SummaryFrequency.MONTHLY.toString();
    private String dataType;
}
