package org.entando.plugins.pda.core.service.summary;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.entando.plugins.pda.core.exception.SummaryTypeNotFoundException;
import org.entando.plugins.pda.core.service.summary.processor.SummaryProcessor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final List<SummaryProcessor> summaryProcessors;

    public List<String> listSummaryTypes() {
        return summaryProcessors
                .stream()
                .map(SummaryProcessor::getType)
                .collect(Collectors.toList());
    }

    public SummaryProcessor getSummaryProcessor(String type) {
        return summaryProcessors.stream()
                .filter(p -> p.getType().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(SummaryTypeNotFoundException::new);
    }
}
