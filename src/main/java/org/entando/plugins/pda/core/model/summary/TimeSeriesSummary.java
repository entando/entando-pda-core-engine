package org.entando.plugins.pda.core.model.summary;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeSeriesSummary implements Summary {
    private String dataType;
    private List<PeriodicSummary> values;
    private CardSummary card;
}
