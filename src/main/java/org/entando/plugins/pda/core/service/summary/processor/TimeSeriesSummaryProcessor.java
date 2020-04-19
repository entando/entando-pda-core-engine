package org.entando.plugins.pda.core.service.summary.processor;

import java.util.List;
import java.util.stream.Collectors;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.summary.CardSummary;
import org.entando.plugins.pda.core.model.summary.PeriodicData;
import org.entando.plugins.pda.core.model.summary.Summary;
import org.entando.plugins.pda.core.model.summary.SummaryFrequency;
import org.entando.plugins.pda.core.model.summary.TimeSeriesData;
import org.entando.plugins.pda.core.model.summary.TimeSeriesSummary;
import org.entando.plugins.pda.core.service.summary.DataRepository;
import org.entando.plugins.pda.core.service.summary.DataService;
import org.entando.plugins.pda.core.service.summary.request.TimeSeriesSummaryRequest;
import org.springframework.stereotype.Component;

@Component
public class TimeSeriesSummaryProcessor extends AbstractSummaryProcessor {

    public static final String TYPE = "TimeSeries";

    public TimeSeriesSummaryProcessor(DataService dataService) {
        super(TYPE, dataService);
    }

    @Override
    public Summary getSummary(Connection connection, String request) {
        TimeSeriesSummaryRequest timeSeriesRequest = convertRequestObject(request, TimeSeriesSummaryRequest.class);
        SummaryFrequency frequency = SummaryFrequency.from(timeSeriesRequest.getFrequency());
        Integer periods = timeSeriesRequest.getPeriods();

        List<TimeSeriesData> result = timeSeriesRequest.getSeries()
                .parallelStream()
                .map(type -> {
                    DataRepository dataRepository = getDataRepository(connection.getEngine(), type);
                    List<PeriodicData> values = dataRepository.getPeriodicData(connection, frequency, periods);
                    double lastValue = 0.0;
                    if (!values.isEmpty()) {
                        lastValue = values.get(0).getValue();
                    }
                    double previousValue = 0.0;
                    if (values.size() > 1) { // NOPMD
                        previousValue = values.get(1).getValue();
                    }

                    return TimeSeriesData.builder()
                            .id(dataRepository.getId())
                            .values(values)
                            .card(CardSummary.builder()
                                    .value(lastValue)
                                    .previousValue(previousValue)
                                    .build())
                            .build();
                })
                .collect(Collectors.toList());

        return TimeSeriesSummary.builder()
                .series(result)
                .build();
    }
}
