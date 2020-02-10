package org.entando.plugins.pda.core.service.summary.processor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.entando.plugins.pda.core.utils.TestUtils.createCardSummary;
import static org.entando.plugins.pda.core.utils.TestUtils.createDataRepository;
import static org.entando.plugins.pda.core.utils.TestUtils.createPeriodicSummary;
import static org.entando.plugins.pda.core.utils.TestUtils.getDummyConnection;
import static org.entando.plugins.pda.core.utils.TestUtils.readFromFile;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.entando.plugins.pda.core.model.summary.CardSummary;
import org.entando.plugins.pda.core.model.summary.PeriodicData;
import org.entando.plugins.pda.core.model.summary.SummaryFrequency;
import org.entando.plugins.pda.core.service.summary.DataRepository;
import org.entando.plugins.pda.core.service.summary.DataService;
import org.entando.plugins.pda.core.service.summary.request.CardSummaryRequest;
import org.junit.Before;
import org.junit.Test;

public class CardSummaryProcessorTest {

    private static final String TYPE_1 = "Type1";
    private static final List<PeriodicData> SUMMARY_DAILY = createPeriodicSummary(SummaryFrequency.DAILY, 2);
    private static final List<PeriodicData> SUMMARY_MONTHLY = createPeriodicSummary(SummaryFrequency.MONTHLY, 2);
    private static final List<PeriodicData> SUMMARY_ANNUALLY = createPeriodicSummary(SummaryFrequency.ANNUALLY, 2);

    private DataRepository dataRepository;
    private CardSummaryProcessor processor;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Before
    public void setUp() {
        dataRepository = createDataRepository(TYPE_1, SUMMARY_DAILY);

        DataService dataService = mock(DataService.class);
        when(dataService.getDataRepository(any(), any())).thenReturn(dataRepository);

        processor = new CardSummaryProcessor(dataService);
    }

    @Test
    public void shouldCreateCardSummaryWithDailyFrequency() throws Exception {
        //Given
        when(dataRepository.getPeriodicData(any(), any(), any()))
                .thenReturn(SUMMARY_DAILY);

        CardSummaryRequest request = CardSummaryRequest.builder()
                .type(TYPE_1)
                .frequency(SummaryFrequency.DAILY.toString())
                .build();

        CardSummary expected = createCardSummary(SUMMARY_DAILY, dataRepository);

        //When
        CardSummary result = (CardSummary) processor
                .getSummary(getDummyConnection(), MAPPER.writeValueAsString(request));

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldCreateCardSummaryWithMonthlyFrequency() throws Exception {
        //Given
        when(dataRepository.getPeriodicData(any(), any(), any()))
                .thenReturn(SUMMARY_MONTHLY);

        CardSummaryRequest request = CardSummaryRequest.builder()
                .type(TYPE_1)
                .frequency(SummaryFrequency.MONTHLY.toString())
                .build();

        CardSummary expected = createCardSummary(SUMMARY_MONTHLY, dataRepository);

        //When
        CardSummary result = (CardSummary) processor
                .getSummary(getDummyConnection(), MAPPER.writeValueAsString(request));

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldCreateCardSummaryWithYearsFrequency() throws Exception {
        //Given
        when(dataRepository.getPeriodicData(any(), any(), any()))
                .thenReturn(SUMMARY_ANNUALLY);

        CardSummaryRequest request = CardSummaryRequest.builder()
                .type(TYPE_1)
                .frequency(SummaryFrequency.ANNUALLY.toString())
                .build();

        CardSummary expected = createCardSummary(SUMMARY_ANNUALLY, dataRepository);

        //When
        CardSummary result = (CardSummary) processor
                .getSummary(getDummyConnection(), MAPPER.writeValueAsString(request));

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldConvertRequestWithOptionalValues() {
        //Given
        String request = readFromFile("card_summary_request_with_optionals.json");
        CardSummaryRequest expected = CardSummaryRequest.builder()
                .frequency(SummaryFrequency.ANNUALLY.toString())
                .type(TYPE_1.toLowerCase())
                .build();

        //When
        CardSummaryRequest result = processor.convertRequestObject(request, CardSummaryRequest.class);

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldConvertRequestWithoutOptionalValues() {
        //Given
        String request = readFromFile("card_summary_request_without_optionals.json");
        CardSummaryRequest expected = CardSummaryRequest.builder()
                .frequency(SummaryFrequency.MONTHLY.toString())
                .type(TYPE_1.toLowerCase())
                .build();

        //When
        CardSummaryRequest result = processor.convertRequestObject(request, CardSummaryRequest.class);

        //Then
        assertThat(result).isEqualTo(expected);
    }

}
