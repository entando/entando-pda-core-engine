package org.entando.plugins.pda.core.service.summary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.groups.Tuple;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.SummaryTypeNotFoundException;
import org.entando.plugins.pda.core.model.summary.FrequencyEnum;
import org.entando.plugins.pda.core.model.summary.Summary;
import org.entando.plugins.pda.core.model.summary.SummaryType;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SummaryServiceTest {

    private static final String TEST_ENGINE = "test";
    private static final String TEST2_ENGINE = "test2";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private SummaryService summaryService;

    @Before
    public void setUp() {
        summaryService = new SummaryService(new ArrayList<>());
    }

    @Test
    public void shouldInitializeSummaryTypesFromConstructor() {
        // Given
        SummaryType test1 = newSummaryType(TEST_ENGINE, "test1", Summary.builder().build());
        SummaryType test2 = newSummaryType(TEST_ENGINE, "test2", Summary.builder().build());
        SummaryType test3 = newSummaryType(TEST2_ENGINE, "test3", Summary.builder().build());
        summaryService = new SummaryService(Arrays.asList(test1, test2, test3));

        // When
        List<SummaryType> summaryTypes = summaryService.getAllSummaryTypes();

        // Then
        assertThat(summaryTypes.size()).isEqualTo(3);
        assertThat(summaryTypes).extracting(SummaryType::getEngine, SummaryType::getId)
                .containsExactlyInAnyOrder(new Tuple(TEST_ENGINE, "test1"),
                        new Tuple(TEST_ENGINE, "test2"),
                        new Tuple(TEST2_ENGINE, "test3"));
    }

    @Test
    public void shouldRegisterNewSummaryType() {
        // Given
        String id = "requests";
        Summary summary = Summary.builder().title("Requests").percentage("98").total("2567")
                .totalLabel("Total Requests").build();
        SummaryType summaryType = newSummaryType(TEST_ENGINE, id, summary);

        // When
        summaryService.registerSummaryType(summaryType);

        // Then
        List<SummaryType> summaryTypes = summaryService.getSummaryTypesByEngine(TEST_ENGINE);
        assertThat(summaryTypes.size()).isEqualTo(1);
        assertThat(summaryTypes.get(0).getId()).isEqualTo(summaryType.getId());
        assertThat(summaryTypes.get(0).getDescription()).isEqualTo(summaryType.getDescription());
        assertThat(summaryTypes.get(0).calculateSummary(Connection.builder().build(), FrequencyEnum.ANNUALLY))
                .isEqualTo(summary);
    }

    @Test
    public void shouldReturnSummaryTypeById() {
        // Given
        Summary requests = Summary.builder().title("Requests").totalLabel("Total Requests")
                .total("2567").percentage("98").build();
        String requestsId = "requests";
        summaryService.registerSummaryType(newSummaryType(TEST_ENGINE, requestsId, requests));
        summaryService.registerSummaryType(newSummaryType(TEST_ENGINE, "cases",
                Summary.builder().title("Cases").totalLabel("Total Cases").total("80800").percentage("20")
                        .build()));
        summaryService.registerSummaryType(newSummaryType(TEST_ENGINE, "time_to_complete",
                Summary.builder().title("Time to Complete").totalLabel("Case creation to completion").total("2.5 days")
                        .percentage("44").build()));

        // When
        SummaryType summaryType = summaryService.getSummaryTypeById(TEST_ENGINE, requestsId);

        // Then
        assertThat(summaryType.getId()).isEqualTo(requestsId);
        assertThat(summaryType.getDescription()).isEqualTo(requests.getTitle());
        assertThat(
                summaryType.calculateSummary(Connection.builder().engine(TEST_ENGINE).build(), FrequencyEnum.MONTHLY))
                .extracting(Summary::getTitle, Summary::getTotalLabel, Summary::getTotal, Summary::getPercentage)
                .contains(requests.getTitle(), requests.getTotalLabel(), requests.getTotal(), requests.getPercentage());
    }

    @Test
    public void shouldCalculateSummary() {
        // Given
        Summary requests = Summary.builder().title("Requests").totalLabel("Total Requests")
                .total("2567").percentage("98").build();
        String requestsId = "requests";
        summaryService.registerSummaryType(newSummaryType(TEST_ENGINE, requestsId, requests));

        // When
        Connection connection = Connection.builder().engine(TEST_ENGINE).build(); // NOPMD
        Summary summary = summaryService.calculateSummary(connection, requestsId, FrequencyEnum.DAILY);

        assertThat(requests).isEqualTo(summary);
    }

    @Test
    public void shouldThrowSummaryTypeNotFoundException() {
        expectedException.expect(SummaryTypeNotFoundException.class);
        expectedException.expectMessage(SummaryTypeNotFoundException.MESSAGE_KEY);

        summaryService.calculateSummary(Connection.builder().build(), "invalid", FrequencyEnum.MONTHLY);
    }

    private SummaryType newSummaryType(String engine, String id, Summary summary) {
        SummaryType summaryType = mock(SummaryType.class);
        when(summaryType.calculateSummary(any(), any(FrequencyEnum.class))).thenReturn(
                summary);
        when(summaryType.getId()).thenReturn(id);
        when(summaryType.getDescription()).thenReturn(summary.getTitle());
        when(summaryType.getEngine()).thenReturn(engine);
        return summaryType;
    }
}
