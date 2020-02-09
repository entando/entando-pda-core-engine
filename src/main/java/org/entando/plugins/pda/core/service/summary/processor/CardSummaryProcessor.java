package org.entando.plugins.pda.core.service.summary.processor;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.summary.CardSummary;
import org.entando.plugins.pda.core.model.summary.PeriodicSummary;
import org.entando.plugins.pda.core.model.summary.Summary;
import org.entando.plugins.pda.core.model.summary.SummaryFrequency;
import org.entando.plugins.pda.core.service.summary.DataType;
import org.entando.plugins.pda.core.service.summary.DataTypeService;
import org.entando.plugins.pda.core.service.summary.request.CardSummaryRequest;
import org.springframework.stereotype.Component;

@Component
public class CardSummaryProcessor extends AbstractSummaryProcessor implements SummaryProcessor {
    public static final String TYPE = "Card";

    public CardSummaryProcessor(DataTypeService dataTypeService) {
        super(TYPE, dataTypeService);
    }

    @Override
    public Summary getSummary(Connection connection, String request) {
        CardSummaryRequest cardRequest = convertRequestObject(request, CardSummaryRequest.class);
        DataType dataType = getDataType(connection.getEngine(), cardRequest.getDataType());
        SummaryFrequency frequency = SummaryFrequency.from(cardRequest.getFrequency());

        List<PeriodicSummary> periods = dataType.getPeriodicSummary(connection, frequency, 2);

        return CardSummary.builder()
                .value(periods.get(0).getValue())
                .previousValue(periods.get(1).getValue())
                .build();
    }
}
