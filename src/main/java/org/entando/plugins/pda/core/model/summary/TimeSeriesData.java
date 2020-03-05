package org.entando.plugins.pda.core.model.summary;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeSeriesData {
    private String id;
    private List<PeriodicData> values;
    private CardSummary card;
}
