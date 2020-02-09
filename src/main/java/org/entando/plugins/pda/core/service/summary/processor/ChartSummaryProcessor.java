package org.entando.plugins.pda.core.service.summary.processor;

import java.util.List;
import java.util.stream.Collectors;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.summary.CardSummary;
import org.entando.plugins.pda.core.model.summary.ChartSummary;
import org.entando.plugins.pda.core.model.summary.PeriodicSummary;
import org.entando.plugins.pda.core.model.summary.Summary;
import org.entando.plugins.pda.core.model.summary.SummaryFrequency;
import org.entando.plugins.pda.core.model.summary.TimeSeriesSummary;
import org.entando.plugins.pda.core.service.summary.DataType;
import org.entando.plugins.pda.core.service.summary.DataTypeService;
import org.entando.plugins.pda.core.service.summary.request.ChartSummaryRequest;
import org.springframework.stereotype.Component;

@Component
public class ChartSummaryProcessor extends AbstractSummaryProcessor implements SummaryProcessor {
    public static final String TYPE = "Chart";

    public ChartSummaryProcessor(DataTypeService dataTypeService) {
        super(TYPE, dataTypeService);
    }

    @Override
    public Summary getSummary(Connection connection, String request) {
        ChartSummaryRequest chartRequest = convertRequestObject(request, ChartSummaryRequest.class);
        SummaryFrequency frequency = SummaryFrequency.from(chartRequest.getFrequency());
        Integer periods = chartRequest.getPeriods();

        List<TimeSeriesSummary> result = chartRequest.getSeries()
                .parallelStream()
                .map(dataTypeId -> {
                    DataType dataType = getDataType(connection.getEngine(), dataTypeId);
                    List<PeriodicSummary> values = dataType.getPeriodicSummary(connection, frequency, periods);
                    PeriodicSummary lastValue = values.get(0);
                    PeriodicSummary previousValue = values.get(1);

                    return TimeSeriesSummary.builder()
                            .dataType(dataType.getId())
                            .values(values)
                            .card(CardSummary.builder()
                                    .value(lastValue.getValue())
                                    .previousValue(previousValue.getValue())
                                    .build())
                            .build();
                })
                .collect(Collectors.toList());


        return ChartSummary.builder()
                .series(result)
                .build();
    }
}
