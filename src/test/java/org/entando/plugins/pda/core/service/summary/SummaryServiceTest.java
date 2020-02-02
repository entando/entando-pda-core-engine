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
import org.entando.plugins.pda.core.model.summary.SummaryValue;
import org.entando.plugins.pda.core.model.summary.ValuePercentageSummaryValue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
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
        Summary test1 = newSummary(TEST_ENGINE, "test1", ValuePercentageSummaryValue.builder().build());
        Summary test2 = newSummary(TEST_ENGINE, "test2", ValuePercentageSummaryValue.builder().build());
        Summary test3 = newSummary(TEST2_ENGINE, "test3", ValuePercentageSummaryValue.builder().build());
        summaryService = new SummaryService(Arrays.asList(test1, test2, test3));

        // When
        List<Summary> summaries = summaryService.getAllSummaries();

        // Then
        assertThat(summaries.size()).isEqualTo(3);
        assertThat(summaries).extracting(Summary::getEngine, Summary::getId)
                .containsExactlyInAnyOrder(new Tuple(TEST_ENGINE, "test1"),
                        new Tuple(TEST_ENGINE, "test2"),
                        new Tuple(TEST2_ENGINE, "test3"));
    }

    @Test
    public void shouldRegisterNewSummaryType() {
        // Given
        String id = "requests";
        ValuePercentageSummaryValue summaryValue = ValuePercentageSummaryValue.builder().title("Requests").percentage("98").total("2567")
                .totalLabel("Total Requests").build();
        Summary summary = newSummary(TEST_ENGINE, id, summaryValue);

        // When
        summaryService.registerSummary(summary);

        // Then
        List<Summary> summaries = summaryService.getSummariesByEngine(TEST_ENGINE);
        assertThat(summaries.size()).isEqualTo(1);
        assertThat(summaries.get(0).getId()).isEqualTo(summary.getId());
        assertThat(summaries.get(0).getDescription()).isEqualTo(summary.getDescription());
        assertThat(summaries.get(0).calculateSummary(Connection.builder().build(), FrequencyEnum.ANNUALLY))
                .isEqualTo(summaryValue);
    }

    @Test
    public void shouldReturnSummaryTypeById() {
        // Given
        ValuePercentageSummaryValue requests = ValuePercentageSummaryValue.builder().title("Requests").totalLabel("Total Requests")
                .total("2567").percentage("98").build();
        String requestsId = "requests";
        summaryService.registerSummary(newSummary(TEST_ENGINE, requestsId, requests));
        summaryService.registerSummary(newSummary(TEST_ENGINE, "cases",
                ValuePercentageSummaryValue.builder().title("Cases").totalLabel("Total Cases").total("80800").percentage("20")
                        .build()));
        summaryService.registerSummary(newSummary(TEST_ENGINE, "time_to_complete",
                ValuePercentageSummaryValue.builder().title("Time to Complete").totalLabel("Case creation to completion").total("2.5 days")
                        .percentage("44").build()));

        // When
        Summary summary = summaryService.getSummaryById(TEST_ENGINE, requestsId);

        // Then
        assertThat(summary.getId()).isEqualTo(requestsId);
        assertThat(summary.getDescription()).isEqualTo(requests.getTitle());
        assertThat(
                (ValuePercentageSummaryValue)summary.calculateSummary(Connection.builder().engine(TEST_ENGINE).build(), FrequencyEnum.MONTHLY))
                .extracting(ValuePercentageSummaryValue::getTitle, ValuePercentageSummaryValue::getTotalLabel, ValuePercentageSummaryValue::getTotal, ValuePercentageSummaryValue::getPercentage)
                .contains(requests.getTitle(), requests.getTotalLabel(), requests.getTotal(), requests.getPercentage());
    }

    @Test
    public void shouldCalculateSummary() {
        // Given
        ValuePercentageSummaryValue requests = ValuePercentageSummaryValue.builder().title("Requests").totalLabel("Total Requests")
                .total("2567").percentage("98").build();
        String requestsId = "requests";
        summaryService.registerSummary(newSummary(TEST_ENGINE, requestsId, requests));

        // When
        Connection connection = Connection.builder().engine(TEST_ENGINE).build(); //NOPMD
        SummaryValue summary = summaryService.calculateSummary(connection, requestsId, FrequencyEnum.DAILY);

        assertThat(requests).isEqualTo(summary);
    }

    @Test
    public void shouldThrowSummaryTypeNotFoundException() {
        expectedException.expect(SummaryTypeNotFoundException.class);
        expectedException.expectMessage(SummaryTypeNotFoundException.MESSAGE_KEY);

        summaryService.calculateSummary(Connection.builder().build(), "invalid", FrequencyEnum.MONTHLY);
    }

    @Test
    public void shouldGetSummaryTypesByEngine() {

        //Test empty

        Connection connection = Connection.builder().engine(TEST_ENGINE).build(); //NOPMD
        List<SummaryType> types = summaryService.getSummaryTypesByEngine(connection.getEngine());
        assertThat(0).isEqualTo(types.size());

        //Test valid entry
        // Given
        ValuePercentageSummaryValue requests = ValuePercentageSummaryValue.builder().title("Requests").totalLabel("Total Requests")
                .total("2567").percentage("98").build();

        String requestsId = "requests";
        Summary newSummary = newSummary(TEST_ENGINE, requestsId, requests);

        summaryService.registerSummary(newSummary);

        // When
        List<SummaryType> populatedTypes = summaryService.getSummaryTypesByEngine(connection.getEngine());
        assertThat(1).isEqualTo(populatedTypes.size());
        assertThat(newSummary.getSummaryType()).isEqualTo(populatedTypes.get(0));

    }

    @Test
    public void shouldGetSummariesByEngineAndSummaryType() {

        //Test empty

        Connection connection = Connection.builder().engine(TEST_ENGINE).build(); //NOPMD
        List<Summary> types = summaryService.getSummariesByEngineAndSummaryType(connection.getEngine(), SummaryType.VALUE_PERCENTAGE.name());
        assertThat(0).isEqualTo(types.size());

        //Test valid entry
        // Given
        ValuePercentageSummaryValue requests = ValuePercentageSummaryValue.builder().title("Requests").totalLabel("Total Requests")
                .total("2567").percentage("98").build();
        String requestsId = "requests";
        Summary newSummary = newSummary(TEST_ENGINE, requestsId, requests);

        summaryService.registerSummary(newSummary);

        // When
        List<Summary> populatedTypes = summaryService.getSummariesByEngineAndSummaryType(connection.getEngine(), SummaryType.VALUE_PERCENTAGE.name());
        assertThat(1).isEqualTo(populatedTypes.size());
        assertThat(newSummary).isEqualTo(populatedTypes.get(0));

        //Test non-matching type
        List<Summary> badType = summaryService.getSummariesByEngineAndSummaryType(connection.getEngine(), "NOT_A_VALUE");
        assertThat(0).isEqualTo(badType.size());


    }


    private Summary newSummary(String engine, String id, ValuePercentageSummaryValue summaryValue) {
        Summary summary = mock(Summary.class);
        when(summary.calculateSummary(any(), any(FrequencyEnum.class))).thenReturn(
                summaryValue);
        when(summary.getId()).thenReturn(id);
        when(summary.getDescription()).thenReturn(summaryValue.getTitle());
        when(summary.getEngine()).thenReturn(engine);
        when(summary.getSummaryType()).thenReturn(SummaryType.VALUE_PERCENTAGE);
        return summary;
    }
}
