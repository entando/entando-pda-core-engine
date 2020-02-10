package org.entando.plugins.pda.core.model.summary;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TimeSeriesSummary implements Summary {
    private List<TimeSeriesData> series;
}
