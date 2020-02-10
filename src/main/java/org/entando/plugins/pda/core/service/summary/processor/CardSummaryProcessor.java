package org.entando.plugins.pda.core.service.summary.processor;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.summary.CardSummary;
import org.entando.plugins.pda.core.model.summary.PeriodicData;
import org.entando.plugins.pda.core.model.summary.Summary;
import org.entando.plugins.pda.core.model.summary.SummaryFrequency;
import org.entando.plugins.pda.core.service.summary.DataRepository;
import org.entando.plugins.pda.core.service.summary.DataService;
import org.entando.plugins.pda.core.service.summary.request.CardSummaryRequest;
import org.springframework.stereotype.Component;

@Component
public class CardSummaryProcessor extends AbstractSummaryProcessor {
    public static final String TYPE = "Card";

    public CardSummaryProcessor(DataService dataService) {
        super(TYPE, dataService);
    }

    @Override
    public Summary getSummary(Connection connection, String request) {
        CardSummaryRequest cardRequest = convertRequestObject(request, CardSummaryRequest.class);
        DataRepository dataRepository = getDataRepository(connection.getEngine(), cardRequest.getType());
        SummaryFrequency frequency = SummaryFrequency.from(cardRequest.getFrequency());

        List<PeriodicData> periods = dataRepository.getPeriodicData(connection, frequency, 2);

        return CardSummary.builder()
                .value(periods.get(0).getValue())
                .previousValue(periods.get(1).getValue())
                .build();
    }
}
