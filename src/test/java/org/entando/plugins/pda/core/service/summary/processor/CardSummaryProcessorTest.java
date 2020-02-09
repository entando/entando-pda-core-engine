package org.entando.plugins.pda.core.service.summary.processor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.entando.plugins.pda.core.utils.TestUtils.createCardSummary;
import static org.entando.plugins.pda.core.utils.TestUtils.createDataType;
import static org.entando.plugins.pda.core.utils.TestUtils.createPeriodicSummary;
import static org.entando.plugins.pda.core.utils.TestUtils.getDummyConnection;
import static org.entando.plugins.pda.core.utils.TestUtils.readFromFile;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.entando.plugins.pda.core.model.summary.CardSummary;
import org.entando.plugins.pda.core.model.summary.PeriodicSummary;
import org.entando.plugins.pda.core.model.summary.SummaryFrequency;
import org.entando.plugins.pda.core.service.summary.DataType;
import org.entando.plugins.pda.core.service.summary.DataTypeService;
import org.entando.plugins.pda.core.service.summary.request.CardSummaryRequest;
import org.junit.Before;
import org.junit.Test;

public class CardSummaryProcessorTest {

    private static final String TYPE_1 = "Type1";
    private static final List<PeriodicSummary> SUMMARY_DAILY = createPeriodicSummary(SummaryFrequency.DAILY, 2);
    private static final List<PeriodicSummary> SUMMARY_MONTHLY = createPeriodicSummary(SummaryFrequency.MONTHLY, 2);
    private static final List<PeriodicSummary> SUMMARY_ANNUALLY = createPeriodicSummary(SummaryFrequency.ANNUALLY, 2);

    private DataType dataType;
    private CardSummaryProcessor processor;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Before
    public void setUp() {
        dataType = createDataType(TYPE_1, SUMMARY_DAILY);

        DataTypeService dataTypeService = mock(DataTypeService.class);
        when(dataTypeService.getDataType(any(), any())).thenReturn(dataType);

        processor = new CardSummaryProcessor(dataTypeService);
    }

    @Test
    public void shouldCreateCardSummaryWithDailyFrequency() throws Exception {
        //Given
        when(dataType.getPeriodicSummary(any(), any(), any()))
                .thenReturn(SUMMARY_DAILY);

        CardSummaryRequest request = CardSummaryRequest.builder()
                .dataType(TYPE_1)
                .frequency(SummaryFrequency.DAILY.toString())
                .build();

        CardSummary expected = createCardSummary(SUMMARY_DAILY, dataType);

        //When
        CardSummary result = (CardSummary) processor
                .getSummary(getDummyConnection(), MAPPER.writeValueAsString(request));

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldCreateCardSummaryWithMonthlyFrequency() throws Exception {
        //Given
        when(dataType.getPeriodicSummary(any(), any(), any()))
                .thenReturn(SUMMARY_MONTHLY);

        CardSummaryRequest request = CardSummaryRequest.builder()
                .dataType(TYPE_1)
                .frequency(SummaryFrequency.MONTHLY.toString())
                .build();

        CardSummary expected = createCardSummary(SUMMARY_MONTHLY, dataType);

        //When
        CardSummary result = (CardSummary) processor
                .getSummary(getDummyConnection(), MAPPER.writeValueAsString(request));

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldCreateCardSummaryWithYearsFrequency() throws Exception {
        //Given
        when(dataType.getPeriodicSummary(any(), any(), any()))
                .thenReturn(SUMMARY_ANNUALLY);

        CardSummaryRequest request = CardSummaryRequest.builder()
                .dataType(TYPE_1)
                .frequency(SummaryFrequency.ANNUALLY.toString())
                .build();

        CardSummary expected = createCardSummary(SUMMARY_ANNUALLY, dataType);

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
                .dataType(TYPE_1.toLowerCase())
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
                .dataType(TYPE_1.toLowerCase())
                .build();

        //When
        CardSummaryRequest result = processor.convertRequestObject(request, CardSummaryRequest.class);

        //Then
        assertThat(result).isEqualTo(expected);
    }

}
