package org.entando.plugins.pda.core.service.summary;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.SummaryTypeNotFoundException;
import org.entando.plugins.pda.core.model.summary.FrequencyEnum;
import org.entando.plugins.pda.core.model.summary.Summary;
import org.entando.plugins.pda.core.model.summary.SummaryType;
import org.entando.plugins.pda.core.model.summary.SummaryValue;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final List<Summary> summaries;

    public void registerSummary(Summary summary) {
        summaries.add(summary);
    }

    public List<Summary> getAllSummaries() {
        return summaries;
    }

    public List<Summary> getSummariesByEngine(String engine) {
        return summaries.stream()
                .filter(e -> e.getEngine().equals(engine))
                .collect(Collectors.toList());
    }

    public Summary getSummaryById(String engine, String id) {
        return summaries.stream()
                .filter(e -> e.getEngine().equals(engine) && e.getId().equals(id))
                .findFirst()
                .orElseThrow(SummaryTypeNotFoundException::new);
    }

    public SummaryValue calculateSummary(Connection connection, String id, FrequencyEnum frequencyEnum) {
        Summary summary = getSummaryById(connection.getEngine(), id);
        return summary.calculateSummary(connection, frequencyEnum);
    }

    public List<SummaryType> getSummaryTypesByEngine(String engine) {
        return summaries.stream()
                .filter(e -> e.getEngine().equals(engine))
                .map(e -> e.getSummaryType())
                .collect(Collectors.toList());
    }

    public List<Summary> getSummariesByEngineAndSummaryType(String engine, String summaryType) {
        return summaries.stream()
                .filter(e -> e.getEngine().equals(engine) && e.getSummaryType().name().equals(summaryType))
                .collect(Collectors.toList());
    }
}
