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
import org.entando.plugins.pda.core.model.summary.PeriodicData;
import org.entando.plugins.pda.core.model.summary.SummaryFrequency;
import org.entando.plugins.pda.core.model.summary.TimeSeriesSummary;
import org.entando.plugins.pda.core.service.summary.DataRepository;
import org.entando.plugins.pda.core.service.summary.DataService;
import org.entando.plugins.pda.core.service.summary.request.TimeSeriesSummaryRequest;
import org.junit.Before;
import org.junit.Test;

public class TimeSeriesSummaryProcessorTest {

    private static final String TYPE_1 = "Type1";
    private static final String TYPE_2 = "Type2";
    private static final List<PeriodicData> SUMMARY_DAILY_1 = createPeriodicSummary(SummaryFrequency.DAILY, 30);
    private static final List<PeriodicData> SUMMARY_DAILY_2 = createPeriodicSummary(SummaryFrequency.DAILY, 30);
    private static final List<PeriodicData> SUMMARY_MONTHLY_1 = createPeriodicSummary(SummaryFrequency.MONTHLY, 12);
    private static final List<PeriodicData> SUMMARY_MONTHLY_2 = createPeriodicSummary(SummaryFrequency.MONTHLY, 12);
    private static final List<PeriodicData> SUMMARY_ANNUALLY_1 = createPeriodicSummary(SummaryFrequency.ANNUALLY, 5);
    private static final List<PeriodicData> SUMMARY_ANNUALLY_2 = createPeriodicSummary(SummaryFrequency.ANNUALLY, 5);

    private DataRepository dataRepository1;
    private DataRepository dataRepository2;
    private TimeSeriesSummaryProcessor processor;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Before
    public void setUp() {
        dataRepository1 = createDataType(TYPE_1, SUMMARY_DAILY_1);
        dataRepository2 = createDataType(TYPE_2, SUMMARY_DAILY_2);

        DataService dataService = mock(DataService.class);
        when(dataService.getDataRepository(any(), eq(TYPE_1))).thenReturn(dataRepository1);
        when(dataService.getDataRepository(any(), eq(TYPE_2))).thenReturn(dataRepository2);

        processor = new TimeSeriesSummaryProcessor(dataService);
    }

    @Test
    public void shouldCreateChartSummaryWithDailyFrequency() throws Exception {
        //Given
        when(dataRepository1.getPeriodicData(any(), any(), any()))
                .thenReturn(SUMMARY_DAILY_1);
        when(dataRepository2.getPeriodicData(any(), any(), any()))
                .thenReturn(SUMMARY_DAILY_2);

        TimeSeriesSummaryRequest request = TimeSeriesSummaryRequest.builder()
                .frequency(SummaryFrequency.DAILY.toString())
                .periods(30)
                .series(Arrays.asList(TYPE_1, TYPE_2))
                .build();

        Map<DataRepository, List<PeriodicData>> summaries = new ConcurrentHashMap<>();
        summaries.put(dataRepository1, SUMMARY_DAILY_1);
        summaries.put(dataRepository2, SUMMARY_DAILY_2);

        TimeSeriesSummary expected = createChartSummary(summaries);

        //When
        TimeSeriesSummary result = (TimeSeriesSummary) processor
                .getSummary(getDummyConnection(), MAPPER.writeValueAsString(request));

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldCreateChartSummaryWithMonthlyFrequency() throws Exception {
        //Given
        when(dataRepository1.getPeriodicData(any(), any(), any()))
                .thenReturn(SUMMARY_MONTHLY_1);
        when(dataRepository2.getPeriodicData(any(), any(), any()))
                .thenReturn(SUMMARY_MONTHLY_2);

        TimeSeriesSummaryRequest request = TimeSeriesSummaryRequest.builder()
                .frequency(SummaryFrequency.MONTHLY.toString())
                .periods(12)
                .series(Arrays.asList(TYPE_1, TYPE_2))
                .build();

        Map<DataRepository, List<PeriodicData>> summaries = new ConcurrentHashMap<>();
        summaries.put(dataRepository1, SUMMARY_MONTHLY_1);
        summaries.put(dataRepository2, SUMMARY_MONTHLY_2);

        TimeSeriesSummary expected = createChartSummary(summaries);

        //When
        TimeSeriesSummary result = (TimeSeriesSummary) processor
                .getSummary(getDummyConnection(), MAPPER.writeValueAsString(request));

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldCreateChartSummaryWithYearsFrequency() throws Exception {
        //Given
        when(dataRepository1.getPeriodicData(any(), any(), any()))
                .thenReturn(SUMMARY_ANNUALLY_1);
        when(dataRepository2.getPeriodicData(any(), any(), any()))
                .thenReturn(SUMMARY_ANNUALLY_2);

        TimeSeriesSummaryRequest request = TimeSeriesSummaryRequest.builder()
                .frequency(SummaryFrequency.ANNUALLY.toString())
                .periods(5)
                .series(Arrays.asList(TYPE_1, TYPE_2))
                .build();

        Map<DataRepository, List<PeriodicData>> summaries = new ConcurrentHashMap<>();
        summaries.put(dataRepository1, SUMMARY_ANNUALLY_1);
        summaries.put(dataRepository2, SUMMARY_ANNUALLY_2);

        TimeSeriesSummary expected = createChartSummary(summaries);

        //When
        TimeSeriesSummary result = (TimeSeriesSummary) processor
                .getSummary(getDummyConnection(), MAPPER.writeValueAsString(request));

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldConvertRequestWithOptionalValues() {
        //Given
        String request = readFromFile("timeseries_summary_request_with_optionals.json");
        TimeSeriesSummaryRequest expected = TimeSeriesSummaryRequest.builder()
                .frequency(SummaryFrequency.ANNUALLY.toString())
                .series(Arrays.asList(TYPE_1.toLowerCase(), TYPE_2.toLowerCase()))
                .periods(5)
                .build();

        //When
        TimeSeriesSummaryRequest result = processor.convertRequestObject(request, TimeSeriesSummaryRequest.class);

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldConvertRequestWithoutOptionalValues() {
        //Given
        String request = readFromFile("timeseries_summary_request_without_optionals.json");
        TimeSeriesSummaryRequest expected = TimeSeriesSummaryRequest.builder()
                .frequency(SummaryFrequency.MONTHLY.toString())
                .series(Arrays.asList(TYPE_1.toLowerCase(), TYPE_2.toLowerCase()))
                .periods(12)
                .build();

        //When
        TimeSeriesSummaryRequest result = processor.convertRequestObject(request, TimeSeriesSummaryRequest.class);

        //Then
        assertThat(result).isEqualTo(expected);
    }

}
