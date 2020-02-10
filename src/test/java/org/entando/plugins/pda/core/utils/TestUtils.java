package org.entando.plugins.pda.core.utils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.engine.FakeEngine;
import org.entando.plugins.pda.core.model.summary.CardSummary;
import org.entando.plugins.pda.core.model.summary.PeriodicData;
import org.entando.plugins.pda.core.model.summary.Summary;
import org.entando.plugins.pda.core.model.summary.SummaryFrequency;
import org.entando.plugins.pda.core.model.summary.TimeSeriesData;
import org.entando.plugins.pda.core.model.summary.TimeSeriesSummary;
import org.entando.plugins.pda.core.service.summary.DataRepository;
import org.entando.plugins.pda.core.service.summary.processor.SummaryProcessor;
import org.entando.web.exception.InternalServerException;
import org.keycloak.representations.AccessToken;
import org.springframework.core.io.ClassPathResource;

@SuppressWarnings("PMD.ExcessiveImports")
public abstract class TestUtils {

    public static final String CONTAINER_ID_1 = "container1";

    public static final String TASK_ID_1 = "1";
    public static final String TASK_NAME_1 = "Task 1";
    public static final String TASK_SUBJECT_1 = "Task Subject 1";
    public static final String TASK_COMMENT_ID_1_1 = "t1-c1";
    public static final String TASK_COMMENT_1_1 = "This is a task comment!";
    public static final String TASK_COMMENT_ID_1_2 = "t1-c2";
    public static final String TASK_COMMENT_1_2 = "Whatever he said...";

    public static final String TASK_ID_2 = "2";
    public static final String TASK_NAME_2 = "Task 2";
    public static final String TASK_SUBJECT_2 = "Task Subject 2";
    public static final String TASK_COMMENT_ID_2_1 = "t2-c1";
    public static final String TASK_COMMENT_2_1 = "This is another task comment!";

    public static final String TASK_FORM_ID_1 = "taskForm1";
    public static final String TASK_FORM_TYPE_1 = "formType1";
    public static final String TASK_FORM_ID_2 = "taskForm2";
    public static final String TASK_FORM_TYPE_2 = "formType2";
    public static final String TASK_FORM_PROP_KEY_1 = "reason";
    public static final String TASK_FORM_PROP_KEY_2 = "external";
    public static final String TASK_FORM_PROP_KEY_4 = "subform";
    public static final String TASK_FORM_PROP_KEY_3 = "performance";
    public static final String TASK_FORM_PROP_1 = "Reason";
    public static final String TASK_FORM_PROP_2 = "External";
    public static final String TASK_FORM_PROP_3 = "Sub Form";
    public static final String TASK_FORM_PROP_4 = "Performance";
    public static final String TASK_FORM_PROP_DESCRIPTION_1 = "This is the placeholder for task form field 1";
    public static final String TASK_FORM_PROP_DESCRIPTION_2 = "This is the placeholder for task form field 2";
    public static final String TASK_FORM_PROP_DESCRIPTION_3 = "This is the placeholder for task form field 3";
    public static final String TASK_FORM_PROP_DESCRIPTION_4 = "This is the placeholder for task form field 4";

    public static final String PROCESS_DEFINITION_ID = "part1@part2";

    public static final String PROCESS_ID_1 = "process-1";
    public static final String PROCESS_NAME_1 = "Process 1";

    public static final String PROCESS_ID_2 = "process-2";
    public static final String PROCESS_NAME_2 = "Process 2";

    public static final String PROCESS_FORM_ID_1 = "processForm1";
    public static final String PROCESS_FORM_TYPE_1 = "formType1";
    public static final String PROCESS_FORM_ID_2 = "processForm2";
    public static final String PROCESS_FORM_TYPE_2 = "formType2";
    public static final String PROCESS_FORM_PROP_KEY_1 = "age";
    public static final String PROCESS_FORM_PROP_KEY_2 = "address";
    public static final String PROCESS_FORM_PROP_KEY_3 = "isNew";
    public static final String PROCESS_FORM_PROP_KEY_4 = "subForm";
    public static final String PROCESS_FORM_PROP_1 = "Age of Property";
    public static final String PROCESS_FORM_PROP_2 = "Address";
    public static final String PROCESS_FORM_PROP_3 = "Is Property New?";
    public static final String PROCESS_FORM_PROP_4 = "Applicant";
    public static final String PROCESS_FORM_PROP_DESCRIPTION_1 = "This is the placeholder for form field 1";
    public static final String PROCESS_FORM_PROP_DESCRIPTION_2 = "This is the placeholder for form field 2";
    public static final String PROCESS_FORM_PROP_DESCRIPTION_3 = "This is the placeholder for form field 3";
    public static final String PROCESS_FORM_PROP_DESCRIPTION_4 = "This is the placeholder for form field 4";


    public static AuthenticatedUser getDummyUser() {
        return getDummyUser("test");
    }

    public static AuthenticatedUser getDummyUser(String username) {
        AccessToken token = new AccessToken();
        token.setPreferredUsername(username);
        return new AuthenticatedUser(username, token);
    }

    public static Connection getDummyConnection() {
        return Connection.builder()
                .username("myUsername")
                .password("myPassword")
                .schema("http")
                .host("myurl")
                .port("8080")
                .build();
    }

    public static String randomStringId() {
        return UUID.randomUUID().toString();
    }

    public static Long randomLongId() {
        return Long.valueOf(RandomStringUtils.randomNumeric(10));
    }

    public static String readFromFile(String filename) {
        try (InputStream is = new ClassPathResource(filename).getInputStream()){
            return IOUtils.toString(is);
        } catch (IOException e) {
            throw new InternalServerException("Error reading file", e);
        }
    }

    public static String minifyJsonString(String prettyJson) throws IOException {
        return new ObjectMapper().readValue(prettyJson, JsonNode.class).toString();
    }

    public static DataRepository createDataType(String type, List<PeriodicData> summary) {
        DataRepository dataRepository = mock(DataRepository.class);
        when(dataRepository.getId()).thenReturn(type);
        when(dataRepository.getEngine()).thenReturn(FakeEngine.TYPE);
        when(dataRepository.getPeriodicData(any(), any(), any()))
                .thenReturn(summary);
        return dataRepository;
    }

    public static SummaryProcessor createSummaryProcessor(String type, Summary summary) {
        SummaryProcessor processor = mock(SummaryProcessor.class);
        when(processor.getSummary(any(), anyString()))
                .thenReturn(summary);
        when(processor.getType()).thenReturn(type);
        return processor;
    }

    public static List<PeriodicData> createPeriodicSummary(SummaryFrequency frequency, int periods) {
        List<PeriodicData> summary = new ArrayList<>();

        LocalDate currDate = LocalDate.now();
        if (frequency == SummaryFrequency.MONTHLY) {
            currDate = currDate.withDayOfMonth(1);
        } else if (frequency == SummaryFrequency.ANNUALLY) {
            currDate = currDate.withMonth(1).withDayOfMonth(1);
        }

        for (int i = 0; i < periods; i++) {
            summary.add(PeriodicData.builder()
                    .date(currDate)
                    .value(RandomUtils.nextDouble(0, 1000))
                    .build());

            if (frequency.equals(SummaryFrequency.DAILY)) {
                currDate = currDate.minusDays(1);
            } else if (frequency.equals(SummaryFrequency.MONTHLY)) {
                currDate = currDate.minusMonths(1);
            } else if (frequency.equals(SummaryFrequency.ANNUALLY)) {
                currDate = currDate.minusYears(1);
            }
        }

        return summary;
    }

    public static CardSummary createCardSummary(List<PeriodicData> values, DataRepository dataRepository) {
        return CardSummary.builder()
                .value(values.get(0).getValue())
                .previousValue(values.get(1).getValue())
                .build();
    }

    public static TimeSeriesSummary createChartSummary(Map<DataRepository, List<PeriodicData>> summaries) {
        List<TimeSeriesData> series = summaries.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey().getId()))
                .map(entry -> {
                    DataRepository dataRepository = entry.getKey();
                    List<PeriodicData> values = entry.getValue();

                    return TimeSeriesData.builder()
                            .id(dataRepository.getId())
                            .values(values)
                            .card(createCardSummary(values, dataRepository))
                            .build();
                })
                .collect(Collectors.toList());

        return TimeSeriesSummary.builder()
                .series(series)
                .build();
    }

}
