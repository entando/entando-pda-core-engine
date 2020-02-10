package org.entando.plugins.pda.core.service.summary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.entando.plugins.pda.core.utils.TestUtils.createSummaryProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.entando.plugins.pda.core.exception.SummaryTypeNotFoundException;
import org.entando.plugins.pda.core.model.summary.CardSummary;
import org.entando.plugins.pda.core.model.summary.TimeSeriesSummary;
import org.entando.plugins.pda.core.service.summary.processor.CardSummaryProcessor;
import org.entando.plugins.pda.core.service.summary.processor.SummaryProcessor;
import org.entando.plugins.pda.core.service.summary.processor.TimeSeriesSummaryProcessor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SummaryServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private SummaryService summaryService;
    private List<SummaryProcessor> summaryProcessors;

    @Before
    public void setUp() {
        summaryProcessors = new ArrayList<>();
        summaryProcessors.add(createSummaryProcessor(CardSummaryProcessor.TYPE, CardSummary.builder().build()));
        summaryProcessors.add(createSummaryProcessor(TimeSeriesSummaryProcessor.TYPE, TimeSeriesSummary.builder().build()));

        summaryService = new SummaryService(summaryProcessors);
    }

    @Test
    public void shouldListSummaryTypes() {
        //Given
        List<String> expected = Arrays.asList(CardSummaryProcessor.TYPE, TimeSeriesSummaryProcessor.TYPE);

        // When
        List<String> result = summaryService.listSummaryTypes();

        // Then
        assertThat(result.size()).isEqualTo(expected.size());
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldGetSummaryProcessor() {
        //Given
        SummaryProcessor expected = summaryProcessors.get(1);

        // When
        SummaryProcessor result = summaryService.getSummaryProcessor(TimeSeriesSummaryProcessor.TYPE);

        // Then
        assertThat(result).isEqualTo(expected);
    }


    @Test
    public void shouldThrowNotFoundWhenInvalidSummaryType() {
        expectedException.expect(SummaryTypeNotFoundException.class);
        summaryService.getSummaryProcessor("invalid");
    }
}
