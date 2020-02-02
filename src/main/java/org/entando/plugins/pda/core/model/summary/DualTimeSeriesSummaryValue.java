package org.entando.plugins.pda.core.model.summary;

import java.util.Date;
import java.util.List;
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
public class DualTimeSeriesSummaryValue implements SummaryValue  {

    private String mainTitle;
    private String barTitle;
    private String lineTitle;

    private ValuePercentageSummaryValue cardOne;
    private ValuePercentageSummaryValue cardTwo;
    private ValuePercentageSummaryValue cardThree;

    private List<Double> barData;
    private List<Double> lineData;
    private List<Date> dateData;
}
