package org.entando.plugins.pda.core.service.summary.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entando.plugins.pda.core.model.summary.SummaryFrequency;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSeriesSummaryRequest {
    private String frequency = SummaryFrequency.MONTHLY.toString();
    private Integer periods;
    private List<String> series;

    public static final int DEFAULT_PERIOD_DAILY = 30;
    public static final int DEFAULT_PERIOD_MONTHLY = 12;
    public static final int DEFAULT_PERIOD_ANNUALLY = 5;

    public Integer getPeriods() {
        if (periods != null) {
            return periods;
        }

        //Should at least be 2
        if (frequency.equals(SummaryFrequency.DAILY.toString())) {
            return DEFAULT_PERIOD_DAILY;
        } else if (frequency.equals(SummaryFrequency.MONTHLY.toString())) {
            return DEFAULT_PERIOD_MONTHLY;
        } else if (frequency.equals(SummaryFrequency.ANNUALLY.toString())) {
            return DEFAULT_PERIOD_ANNUALLY;
        }

        return null;
    }
}
