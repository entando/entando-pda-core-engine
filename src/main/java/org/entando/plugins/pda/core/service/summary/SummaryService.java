package org.entando.plugins.pda.core.service.summary;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.SummaryTypeNotFoundException;
import org.entando.plugins.pda.core.model.summary.FrequencyEnum;
import org.entando.plugins.pda.core.model.summary.Summary;
import org.entando.plugins.pda.core.model.summary.SummaryType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final List<SummaryType> summaryTypes;

    public void registerSummaryType(SummaryType summaryType) {
        summaryTypes.add(summaryType);
    }

    public List<SummaryType> getAllSummaryTypes() {
        return summaryTypes;
    }

    public List<SummaryType> getSummaryTypesByEngine(String engine) {
        return summaryTypes.stream()
                .filter(e -> e.getEngine().equals(engine))
                .collect(Collectors.toList());
    }

    public SummaryType getSummaryTypeById(String engine, String id) {
        return summaryTypes.stream()
                .filter(e -> e.getEngine().equals(engine) && e.getId().equals(id))
                .findFirst()
                .orElseThrow(SummaryTypeNotFoundException::new);
    }

    public Summary calculateSummary(Connection connection, String id, FrequencyEnum frequencyEnum) {
        SummaryType summaryType = getSummaryTypeById(connection.getEngine(), id);
        return summaryType.calculateSummary(connection, frequencyEnum);
    }
}
