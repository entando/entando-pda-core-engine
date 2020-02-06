package org.entando.plugins.pda.core.service.summary.processor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.entando.plugins.pda.core.utils.TestUtils.createChartSummary;
import static org.entando.plugins.pda.core.utils.TestUtils.createDataType;
import static org.entando.plugins.pda.core.utils.TestUtils.createPeriodicSummary;
import static org.entando.plugins.pda.core.utils.TestUtils.getDummyConnection;
import static org.entando.plugins.pda.core.utils.TestUtils.readFromFile;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.entando.plugins.pda.core.model.summary.ChartSummary;
import org.entando.plugins.pda.core.model.summary.PeriodicSummary;
import org.entando.plugins.pda.core.model.summary.SummaryFrequency;
import org.entando.plugins.pda.core.service.summary.DataType;
import org.entando.plugins.pda.core.service.summary.DataTypeService;
import org.entando.plugins.pda.core.service.summary.request.ChartSummaryRequest;
import org.junit.Before;
import org.junit.Test;

public class ChartSummaryProcessorTest {

    private static final String TYPE_1 = "Type1";
    private static final String TYPE_2 = "Type2";
    private static final List<PeriodicSummary> SUMMARY_DAILY_1 = createPeriodicSummary(SummaryFrequency.DAILY, 30);
    private static final List<PeriodicSummary> SUMMARY_DAILY_2 = createPeriodicSummary(SummaryFrequency.DAILY, 30);
    private static final List<PeriodicSummary> SUMMARY_MONTHLY_1 = createPeriodicSummary(SummaryFrequency.MONTHLY, 12);
    private static final List<PeriodicSummary> SUMMARY_MONTHLY_2 = createPeriodicSummary(SummaryFrequency.MONTHLY, 12);
    private static final List<PeriodicSummary> SUMMARY_ANNUALLY_1 = createPeriodicSummary(SummaryFrequency.ANNUALLY, 5);
    private static final List<PeriodicSummary> SUMMARY_ANNUALLY_2 = createPeriodicSummary(SummaryFrequency.ANNUALLY, 5);

    private DataType dataType1;
    private DataType dataType2;
    private ChartSummaryProcessor processor;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Before
    public void setUp() {
        dataType1 = createDataType(TYPE_1, SUMMARY_DAILY_1);
        dataType2 = createDataType(TYPE_2, SUMMARY_DAILY_2);

        DataTypeService dataTypeService = mock(DataTypeService.class);
        when(dataTypeService.getDataType(any(), eq(TYPE_1))).thenReturn(dataType1);
        when(dataTypeService.getDataType(any(), eq(TYPE_2))).thenReturn(dataType2);

        processor = new ChartSummaryProcessor(dataTypeService);
    }

    @Test
    public void shouldCreateChartSummaryWithDailyFrequency() throws Exception {
        //Given
        when(dataType1.getPeriodicSummary(any(), any(), any()))
                .thenReturn(SUMMARY_DAILY_1);
        when(dataType2.getPeriodicSummary(any(), any(), any()))
                .thenReturn(SUMMARY_DAILY_2);

        ChartSummaryRequest request = ChartSummaryRequest.builder()
                .frequency(SummaryFrequency.DAILY.toString())
                .periods(30)
                .series(Arrays.asList(TYPE_1, TYPE_2))
                .build();

        Map<DataType, List<PeriodicSummary>> summaries = new ConcurrentHashMap<>();
        summaries.put(dataType1, SUMMARY_DAILY_1);
        summaries.put(dataType2, SUMMARY_DAILY_2);

        ChartSummary expected = createChartSummary(summaries);

        //When
        ChartSummary result = (ChartSummary) processor
                .getSummary(getDummyConnection(), MAPPER.writeValueAsString(request));

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldCreateChartSummaryWithMonthlyFrequency() throws Exception {
        //Given
        when(dataType1.getPeriodicSummary(any(), any(), any()))
                .thenReturn(SUMMARY_MONTHLY_1);
        when(dataType2.getPeriodicSummary(any(), any(), any()))
                .thenReturn(SUMMARY_MONTHLY_2);

        ChartSummaryRequest request = ChartSummaryRequest.builder()
                .frequency(SummaryFrequency.MONTHLY.toString())
                .periods(12)
                .series(Arrays.asList(TYPE_1, TYPE_2))
                .build();

        Map<DataType, List<PeriodicSummary>> summaries = new ConcurrentHashMap<>();
        summaries.put(dataType1, SUMMARY_MONTHLY_1);
        summaries.put(dataType2, SUMMARY_MONTHLY_2);

        ChartSummary expected = createChartSummary(summaries);

        //When
        ChartSummary result = (ChartSummary) processor
                .getSummary(getDummyConnection(), MAPPER.writeValueAsString(request));

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldCreateChartSummaryWithYearsFrequency() throws Exception {
        //Given
        when(dataType1.getPeriodicSummary(any(), any(), any()))
                .thenReturn(SUMMARY_ANNUALLY_1);
        when(dataType2.getPeriodicSummary(any(), any(), any()))
                .thenReturn(SUMMARY_ANNUALLY_2);

        ChartSummaryRequest request = ChartSummaryRequest.builder()
                .frequency(SummaryFrequency.ANNUALLY.toString())
                .periods(5)
                .series(Arrays.asList(TYPE_1, TYPE_2))
                .build();

        Map<DataType, List<PeriodicSummary>> summaries = new ConcurrentHashMap<>();
        summaries.put(dataType1, SUMMARY_ANNUALLY_1);
        summaries.put(dataType2, SUMMARY_ANNUALLY_2);

        ChartSummary expected = createChartSummary(summaries);

        //When
        ChartSummary result = (ChartSummary) processor
                .getSummary(getDummyConnection(), MAPPER.writeValueAsString(request));

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldConvertRequestWithOptionalValues() {
        //Given
        String request = readFromFile("chart_summary_request_with_optionals.json");
        ChartSummaryRequest expected = ChartSummaryRequest.builder()
                .frequency(SummaryFrequency.ANNUALLY.toString())
                .series(Arrays.asList(TYPE_1.toLowerCase(), TYPE_2.toLowerCase()))
                .periods(5)
                .build();

        //When
        ChartSummaryRequest result = processor.convertRequestObject(request, ChartSummaryRequest.class);

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldConvertRequestWithoutOptionalValues() {
        //Given
        String request = readFromFile("chart_summary_request_without_optionals.json");
        ChartSummaryRequest expected = ChartSummaryRequest.builder()
                .frequency(SummaryFrequency.MONTHLY.toString())
                .series(Arrays.asList(TYPE_1.toLowerCase(), TYPE_2.toLowerCase()))
                .periods(12)
                .build();

        //When
        ChartSummaryRequest result = processor.convertRequestObject(request, ChartSummaryRequest.class);

        //Then
        assertThat(result).isEqualTo(expected);
    }

}
