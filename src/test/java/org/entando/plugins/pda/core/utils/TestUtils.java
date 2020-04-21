
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import org.entando.plugins.pda.core.model.form.Form;
import org.entando.plugins.pda.core.model.form.FormField;
import org.entando.plugins.pda.core.model.form.FormFieldDate;
import org.entando.plugins.pda.core.model.form.FormFieldNumber;
import org.entando.plugins.pda.core.model.form.FormFieldSelector;
import org.entando.plugins.pda.core.model.form.FormFieldSelector.Option;
import org.entando.plugins.pda.core.model.form.FormFieldSubForm;
import org.entando.plugins.pda.core.model.form.FormFieldText;
import org.entando.plugins.pda.core.model.form.FormFieldType;
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

// CPD-OFF
@SuppressWarnings({"PMD.ExcessiveImports", "PMD.TooManyMethods", "PMD.AvoidDuplicateLiterals"})
public abstract class TestUtils {

    public static final String CONTAINER_ID_1 = "container1";
    public static final String CONTAINER_ID_2 = "container2";

    public static final String TASK_ID_1 = "1";
    public static final String TASK_NAME_1 = "Task 1";
    public static final String TASK_SUBJECT_1 = "Task Subject 1";
    public static final String TASK_COMMENT_ID_1_1 = "t1-c1";
    public static final String TASK_COMMENT_1_1 = "This is a task comment!";
    public static final String TASK_COMMENT_ID_1_2 = "t1-c2";
    public static final String TASK_COMMENT_1_2 = "Whatever he said...";
    public static final String TASK_ATTACHMENT_ID_1_1 = "1";
    public static final String TASK_ATTACHMENT_NAME_1_1 = "attachment.pdf";
    public static final String TASK_ATTACHMENT_TYPE_1_1 = "application/pdf";
    public static final String TASK_ATTACHMENT_OWNER_1_1 = "Chuck Norris";
    public static final Date TASK_ATTACHMENT_CREATED_1_1 = new Date();
    public static final Long TASK_ATTACHMENT_SIZE_1_1 = 43_627L;

    public static final String TASK_ATTACHMENT_ID_1_2 = "2";
    public static final String TASK_ATTACHMENT_NAME_1_2 = "image.jpeg";
    public static final String TASK_ATTACHMENT_TYPE_1_2 = "application/jpeg";
    public static final String TASK_ATTACHMENT_OWNER_1_2 = "Bruce Wayne";
    public static final Date TASK_ATTACHMENT_CREATED_1_2 = new Date();
    public static final Long TASK_ATTACHMENT_SIZE_1_2 = 5433L;

    public static final String TASK_ID_2 = "2";
    public static final String TASK_NAME_2 = "Task 2";
    public static final String TASK_SUBJECT_2 = "Task Subject 2";
    public static final String TASK_COMMENT_ID_2_1 = "t2-c1";
    public static final String TASK_COMMENT_2_1 = "This is another task comment!";
    public static final String TASK_ATTACHMENT_ID_2_1 = "3";
    public static final String TASK_ATTACHMENT_NAME_2_1 = "image.jpeg";
    public static final String TASK_ATTACHMENT_TYPE_2_1 = "application/jpeg";
    public static final String TASK_ATTACHMENT_OWNER_2_1 = "Jack Bauer";
    public static final Date TASK_ATTACHMENT_CREATED_2_1 = new Date();
    public static final Long TASK_ATTACHMENT_SIZE_2_1 = 5434L;

    public static final String TASK_ATTACHMENT_NAME_2_2 = "file4.pdf";
    public static final String TASK_ATTACHMENT_OWNER_2_2 = "Jack Bauer";
    public static final Date TASK_ATTACHMENT_CREATED_2_2 = new Date();
    public static final Long TASK_ATTACHMENT_SIZE_2_2 = 50_116L;

    public static final String TASK_ID_3 = "3";
    public static final String TASK_NAME_3 = "Task 3";

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
    public static final String PROCESS_ID_2 = "process-2";
    public static final String PROCESS_FORM_ID_1 = "processForm1";
    public static final String PROCESS_FORM_PROP_KEY_1 = "age";
    public static final String PROCESS_FORM_PROP_KEY_2 = "address";
    public static final String PROCESS_FORM_PROP_1 = "Age of Property";
    public static final String PROCESS_FORM_PROP_2 = "Address";

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
                .url("http://myurl.com:8080")
                .build();
    }

    public static String randomStringId() {
        return UUID.randomUUID().toString();
    }

    public static Long randomLongId() {
        return Long.valueOf(RandomStringUtils.randomNumeric(10));
    }

    public static String readFromFile(String filename) {
        try (InputStream is = new ClassPathResource(filename).getInputStream()) {
            return IOUtils.toString(is);
        } catch (IOException e) {
            throw new InternalServerException("Error reading file", e);
        }
    }

    public static String minifyJsonString(String prettyJson) throws IOException {
        return new ObjectMapper().readValue(prettyJson, JsonNode.class).toString();
    }

    public static DataRepository createDataRepository(String type, List<PeriodicData> summary) {
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

    public static CardSummary createCardSummary(List<PeriodicData> values) {
        return CardSummary.builder()
                .value(values.isEmpty() ? 0.0 : values.get(0).getValue())
                .previousValue(values.size() > 1 ? values.get(1).getValue() : 0.0)
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
                            .card(createCardSummary(values))
                            .build();
                })
                .collect(Collectors.toList());

        return TimeSeriesSummary.builder()
                .series(series)
                .build();
    }

    @SuppressWarnings("PMD.ExcessiveMethodLength")
    public static Form createSimpleProcessForm() {
        List<FormField> fields = new ArrayList<>();

        FormFieldSubForm fieldSubform1 = FormFieldSubForm.builder()
                .id("field_1786956128605089E11")
                .name("applicant")
                .label("Applicant")
                .type(FormFieldType.SUBFORM)
                .formId("applicant")
                .formType("com.myspace.mortgage_app.Applicant")
                .build();

        FormFieldSubForm fieldSubform2 = FormFieldSubForm.builder()
                .id("field_1811697043491191E12")
                .name("property")
                .label("Property")
                .type(FormFieldType.SUBFORM)
                .formId("property")
                .formType("com.myspace.mortgage_app.Property")
                .build();

        fields.add(fieldSubform1);
        fields.add(fieldSubform2);

        fields.add(FormFieldNumber.builder()
                .id("field_290268943445829E11")
                .name("downpayment")
                .label("Down Payment")
                .type(FormFieldType.INTEGER)
                .placeholder("Down Payment")
                .build()
        );

        fields.add(FormFieldNumber.builder()
                .id("field_0343033866589585E12")
                .name("amortization")
                .label("Years of amortization")
                .type(FormFieldType.INTEGER)
                .placeholder("Years of amortization")
                .build()
        );

        Form holder = Form.builder()
                .id("application")
                .type("com.myspace.mortgage_app.Application")
                .name("Application")
                .fields(fields)
                .build();

        List<FormField> fields1 = new ArrayList<>();
        fields1.add(FormFieldText.builder()
                .id("field_922175737010885E11")
                .name("name")
                .label("Name")
                .type(FormFieldType.STRING)
                .placeholder("Name")
                .maxLength(100)
                .build()
        );

        fields1.add(FormFieldNumber.builder()
                .id("field_405154649767496E12")
                .name("annualincome")
                .label("Annual Income")
                .type(FormFieldType.INTEGER)
                .placeholder("Annual Income")
                .build()
        );

        fields1.add(FormFieldNumber.builder()
                .id("field_670713100411637E11")
                .name("ssn")
                .label("SSN")
                .type(FormFieldType.INTEGER)
                .placeholder("SSN")
                .build()
        );

        List<FormField> fields2 = new ArrayList<>();
        fields2.add(FormFieldNumber.builder()
                .id("field_815717729253767E11")
                .name("age")
                .label("Age of property")
                .type(FormFieldType.INTEGER)
                .placeholder("Age of property")
                .build()
        );

        fields2.add(FormFieldText.builder()
                .id("field_236289653097941E11")
                .name("address")
                .label("Address of property")
                .type(FormFieldType.STRING)
                .placeholder("Address of property")
                .maxLength(100)
                .build()
        );

        fields2.add(FormFieldText.builder()
                .id("field_9471909295199063E11")
                .name("locale")
                .label("Locale")
                .type(FormFieldType.STRING)
                .placeholder("Locale")
                .maxLength(100)
                .build()
        );

        fields2.add(FormFieldNumber.builder()
                .id("field_4113393327260706E12")
                .name("saleprice")
                .label("Sale Price")
                .type(FormFieldType.INTEGER)
                .placeholder("Sale Price")
                .build()
        );

        Form subform1 = Form.builder()
                .id("applicant")
                .name("Applicant")
                .type("com.myspace.mortgage_app.Applicant")
                .fields(fields1)
                .build();

        Form subform2 = Form.builder()
                .id("property")
                .name("Property")
                .type("com.myspace.mortgage_app.Property")
                .fields(fields2)
                .build();

        fieldSubform1.setForm(subform1);
        fieldSubform2.setForm(subform2);

        return Form.builder()
                .id("process")
                .name("Mortgage_Process.MortgageApprovalProcess-taskform.frm")
                .fields(Collections.singletonList(
                        FormFieldSubForm.builder()
                                .id("field_0906698901603516E10")
                                .name("application")
                                .label("Application")
                                .type(FormFieldType.SUBFORM)
                                .formId("application")
                                .formType("com.myspace.mortgage_app.Application")
                                .form(holder)
                                .build()))
                .build();
    }

    @SuppressWarnings("PMD.ExcessiveMethodLength")
    public static Form createFullProcessForm() {
        List<FormField> fieldsSubForm = new ArrayList<>();
        fieldsSubForm.add(FormFieldDate.builder()
                .id("field_3423371242937776E12")
                .name("myDateTime")
                .label("My Date Time ")
                .type(FormFieldType.DATE)
                .placeholder("My Date Time ")
                .withTime(true)
                .build()
        );

        fieldsSubForm.add(FormField.builder()
                .id("field_912757066857511E11")
                .name("myBoolean")
                .label("My Boolean")
                .type(FormFieldType.BOOLEAN)
                .build()
        );

        fieldsSubForm.add(FormFieldText.builder()
                .id("field_9383327275491315E11")
                .name("myString")
                .label("My String ")
                .type(FormFieldType.STRING)
                .required(true)
                .maxLength(100)
                .placeholder("My String")
                .build()
        );

        fieldsSubForm.add(FormFieldNumber.builder()
                .id("field_889617218948984E11")
                .name("myDouble")
                .label("My Double - Read Only")
                .type(FormFieldType.DOUBLE)
                .readOnly(true)
                .placeholder("My Double")
                .build()
        );

        fieldsSubForm.add(FormFieldNumber.builder()
                .id("field_5437")
                .name("myInteger")
                .label("My Integer")
                .type(FormFieldType.INTEGER)
                .placeholder("My Integer")
                .build()
        );

        Form subform = Form.builder()
                .id("myObject")
                .name("com_myspace_forms_sample_MyObject")
                .type("com.myspace.forms_sample.MyObject")
                .fields(fieldsSubForm)
                .build();

        List<FormField> fields = new ArrayList<>();

        fields.add(FormField.builder()
                .id("field_4622282256601577E12")
                .name("processDocument")
                .label("Upload a document for the ProcessDocument process variable")
                .type(FormFieldType.DOCUMENT)
                .build());

        fields.add(FormField.builder()
                .id("field_4336228990495896E12")
                .name("processDocumentList")
                .label("Upload a list of documents to the ProcessDocumentList process variable")
                .type(FormFieldType.DOCUMENT_LIST)
                .build());

        FormFieldSubForm fieldSubform = FormFieldSubForm.builder()
                .id("field_2127768227611858E12")
                .name("myObject")
                .label("Nested Form of My Object")
                .type(FormFieldType.SUBFORM)
                .formId("myObject")
                .formType("com.myspace.forms_sample.MyObject")
                .build();

        fields.add(fieldSubform);

        fields.add(FormFieldSelector.builder()
                .id("field_9814")
                .name("__unbound_field_field_9814")
                .label("My Radio Group")
                .type(FormFieldType.RADIO)
                .option(Option.builder()
                        .value("myValue")
                        .label("myText")
                        .build())
                .option(Option.builder()
                        .value("anotherValue")
                        .label("anotherText")
                        .build())
                .option(Option.builder()
                        .value("what?")
                        .label("yup!")
                        .build())
                .build());

        fields.add(FormFieldNumber.builder()
                .id("field_9324")
                .name("__unbound_field_field_9324")
                .label("Slider")
                .type(FormFieldType.SLIDER)
                .minValue(0.0)
                .maxValue(200.0)
                .multipleOf(5.0)
                .build());

        fields.add(FormFieldNumber.builder()
                .id("field_2802")
                .name("__unbound_field_field_2802")
                .label("Slider 0to1")
                .type(FormFieldType.SLIDER)
                .minValue(0.0)
                .maxValue(1.0)
                .multipleOf(0.1)
                .build());

        fields.add(FormFieldText.builder()
                .id("field_6196")
                .name("__unbound_field_field_6196")
                .label("This is a big text")
                .type(FormFieldType.STRING)
                .placeholder("Write here!")
                .required(true)
                .rows(4)
                .build());

        fields.add(FormFieldSelector.builder()
                .id("field_4073")
                .name("__unbound_field_field_4073")
                .label("My Multiple Selector")
                .type(FormFieldType.MULTIPLE)
                .multiple(true)
                .option(Option.builder()
                        .value("first")
                        .label("first")
                        .build())
                .option(Option.builder()
                        .value("second")
                        .label("second")
                        .build())
                .option(Option.builder()
                        .value("third")
                        .label("third")
                        .build())
                .option(Option.builder()
                        .value("last")
                        .label("last")
                        .build())
                .build());

        fields.add(FormFieldSelector.builder()
                .id("field_653")
                .name("__unbound_field_field_653")
                .label("Combo without default")
                .type(FormFieldType.COMBO)
                .option(Option.builder()
                        .value("oneValue")
                        .label("one")
                        .build())
                .option(Option.builder()
                        .value("twoValue")
                        .label("two")
                        .build())
                .option(Option.builder()
                        .value("threeValue")
                        .label("three")
                        .build())
                .build());

        fields.add(FormFieldSelector.builder()
                .id("field_68448")
                .name("__unbound_field_field_68448")
                .label("Combo")
                .type(FormFieldType.COMBO)
                .defaultValue("myValue")
                .option(Option.builder()
                        .value("myValue")
                        .label("myText")
                        .build())
                .option(Option.builder()
                        .value("anotherValue")
                        .label("anotherText")
                        .build())
                .option(Option.builder()
                        .value("what?")
                        .label("yup!")
                        .build())
                .build());

        fields.add(FormField.builder()
                .id("field_72394")
                .name("processList")
                .label("ProcessList")
                .type(FormFieldType.INPUT_LIST)
                .build());

        fields.add(FormFieldSelector.builder()
                .id("field_030592")
                .name("__unbound_field_field_030592")
                .label("My Radio Group with default value")
                .type(FormFieldType.RADIO)
                .required(true)
                .defaultValue("thirdValue")
                .option(Option.builder()
                        .value("firstValue")
                        .label("first")
                        .build())
                .option(Option.builder()
                        .value("secondValue")
                        .label("second")
                        .build())
                .option(Option.builder()
                        .value("thirdValue")
                        .label("third")
                        .build())
                .build());

        fieldSubform.setForm(subform);

        return Form.builder()
                .id("process")
                .name("forms-sample.ProcessSample-taskform.frm")
                .fields(fields)
                .build();
    }

    @SuppressWarnings("PMD.ExcessiveMethodLength")
    public static Form createSimpleTaskForm() {

        List<FormField> holderFields = new ArrayList<>();

        holderFields.add(FormFieldNumber.builder()
                .id("field_4086")
                .name("mortgageamount")
                .label("Mortgage amount")
                .type(FormFieldType.INTEGER)
                .placeholder("Mortgage amount")
                .build()
        );

        holderFields.add(FormFieldNumber.builder()
                .id("field_3875")
                .name("downpayment")
                .label("Down Payment")
                .readOnly(true)
                .type(FormFieldType.INTEGER)
                .placeholder("Down Payment")
                .build()
        );

        holderFields.add(FormFieldNumber.builder()
                .id("field_1184")
                .name("amortization")
                .label("Years of amortization")
                .readOnly(true)
                .type(FormFieldType.INTEGER)
                .placeholder("Years of amortization")
                .build()
        );

        FormFieldSubForm fieldSubform1 = FormFieldSubForm.builder()
                .id("field_7992")
                .name("applicant")
                .label("Applicant")
                .readOnly(true)
                .type(FormFieldType.SUBFORM)
                .formId("applicant")
                .formType("com.myspace.mortgage_app.Applicant")
                .build();

        FormFieldSubForm fieldSubform2 = FormFieldSubForm.builder()
                .id("field_4885")
                .name("property")
                .label("Property")
                .readOnly(true)
                .type(FormFieldType.SUBFORM)
                .formId("property")
                .formType("com.myspace.mortgage_app.Property")
                .build();

        holderFields.add(fieldSubform1);
        holderFields.add(fieldSubform2);

        Form holder = Form.builder()
                .id("Application")
                .type("com.myspace.mortgage_app.Application")
                .name("ApplicationMortgage")
                .fields(holderFields)
                .build();

        List<FormField> fields1 = new ArrayList<>();

        fields1.add(FormFieldText.builder()
                .id("field_922175737010885E11")
                .name("name")
                .label("Name")
                .type(FormFieldType.STRING)
                .placeholder("Name")
                .maxLength(100)
                .build()
        );

        fields1.add(FormFieldNumber.builder()
                .id("field_405154649767496E12")
                .name("annualincome")
                .label("Annual Income")
                .type(FormFieldType.INTEGER)
                .placeholder("Annual Income")
                .build()
        );

        fields1.add(FormFieldNumber.builder()
                .id("field_670713100411637E11")
                .name("ssn")
                .label("SSN")
                .type(FormFieldType.INTEGER)
                .placeholder("SSN")
                .build()
        );

        List<FormField> fields2 = new ArrayList<>();

        fields2.add(FormFieldNumber.builder()
                .id("field_815717729253767E11")
                .name("age")
                .label("Age of property")
                .type(FormFieldType.INTEGER)
                .placeholder("Age of property")
                .build()
        );

        fields2.add(FormFieldText.builder()
                .id("field_236289653097941E11")
                .name("address")
                .label("Address of property")
                .type(FormFieldType.STRING)
                .placeholder("Address of property")
                .maxLength(100)
                .build()
        );

        fields2.add(FormFieldText.builder()
                .id("field_9471909295199063E11")
                .name("locale")
                .label("Locale")
                .type(FormFieldType.STRING)
                .placeholder("Locale")
                .maxLength(100)
                .build()
        );

        fields2.add(FormFieldNumber.builder()
                .id("field_4113393327260706E12")
                .name("saleprice")
                .label("Sale Price")
                .type(FormFieldType.INTEGER)
                .placeholder("Sale Price")
                .build()
        );

        Form subform1 = Form.builder()
                .id("applicant")
                .name("Applicant")
                .type("com.myspace.mortgage_app.Applicant")
                .fields(fields1)
                .build();

        Form subform2 = Form.builder()
                .id("property")
                .name("Property")
                .type("com.myspace.mortgage_app.Property")
                .fields(fields2)
                .build();

        fieldSubform1.setForm(subform1);
        fieldSubform2.setForm(subform2);

        return Form.builder()
                .id("task")
                .name("Qualify-taskform.frm")
                .field(FormFieldSubForm.builder()
                        .id("field_0627466111868674E11")
                        .name("application")
                        .label("Application")
                        .readOnly(true)
                        .type(FormFieldType.SUBFORM)
                        .formId("application")
                        .formType("com.myspace.mortgage_app.Application")
                        .form(holder)
                        .build())
                .field(FormField.builder()
                        .id("field_282450953523892E10")
                        .name("inlimit")
                        .label("Is mortgage application in limit?")
                        .type(FormFieldType.BOOLEAN)
                        .build())
                .build();
    }

    @SuppressWarnings("PMD.ExcessiveMethodLength")
    public static Form createFullTaskForm() {
        List<FormField> fieldsSubForm = new ArrayList<>();
        fieldsSubForm.add(FormFieldDate.builder()
                .id("field_3423371242937776E12")
                .name("myDateTime")
                .label("My Date Time ")
                .type(FormFieldType.DATE)
                .placeholder("My Date Time ")
                .withTime(true)
                .build()
        );

        fieldsSubForm.add(FormField.builder()
                .id("field_912757066857511E11")
                .name("myBoolean")
                .label("My Boolean")
                .type(FormFieldType.BOOLEAN)
                .build()
        );

        fieldsSubForm.add(FormFieldText.builder()
                .id("field_9383327275491315E11")
                .name("myString")
                .label("My String ")
                .type(FormFieldType.STRING)
                .required(true)
                .maxLength(100)
                .placeholder("My String")
                .build()
        );

        fieldsSubForm.add(FormFieldNumber.builder()
                .id("field_889617218948984E11")
                .name("myDouble")
                .label("My Double - Read Only")
                .type(FormFieldType.DOUBLE)
                .readOnly(true)
                .placeholder("My Double")
                .build()
        );

        fieldsSubForm.add(FormFieldNumber.builder()
                .id("field_73505")
                .name("myInteger")
                .label("My Integer")
                .type(FormFieldType.INTEGER)
                .placeholder("My Integer")
                .build()
        );

        Form subform = Form.builder()
                .id("MyObject")
                .name("com_myspace_forms_sample_MyObject")
                .type("com.myspace.forms_sample.MyObject")
                .fields(fieldsSubForm)
                .build();

        List<FormField> fields = new ArrayList<>();

        fields.add(FormField.builder()
                .id("field_0288987185344915E12")
                .name("_processDocument")
                .label("Upload a document")
                .type(FormFieldType.DOCUMENT)
                .build());

        fields.add(FormFieldSelector.builder()
                .id("field_0465988380419236E12")
                .name("_comboWithDefault")
                .label("Combo with default")
                .type(FormFieldType.COMBO)
                .defaultValue("myValue")
                .option(Option.builder()
                        .value("myValue")
                        .label("myText")
                        .build())
                .option(Option.builder()
                        .value("anotherValue")
                        .label("anotherText")
                        .build())
                .option(Option.builder()
                        .value("what?")
                        .label("yup!")
                        .build())
                .build());

        fields.add(FormFieldSelector.builder()
                .id("field_990334777874346E11")
                .name("_comboWithoutDefault")
                .label("Combo without default")
                .type(FormFieldType.COMBO)
                .option(Option.builder()
                        .value("oneValue")
                        .label("one")
                        .build())
                .option(Option.builder()
                        .value("twoValue")
                        .label("two")
                        .build())
                .option(Option.builder()
                        .value("threeValue")
                        .label("three")
                        .build())
                .build());

        fields.add(FormFieldSelector.builder()
                .id("field_535447945936313E11")
                .name("_multipleSelector")
                .label("My Multiple Selector")
                .type(FormFieldType.MULTIPLE)
                .multiple(true)
                .option(Option.builder()
                        .value("first")
                        .label("first")
                        .build())
                .option(Option.builder()
                        .value("second")
                        .label("second")
                        .build())
                .option(Option.builder()
                        .value("third")
                        .label("third")
                        .build())
                .option(Option.builder()
                        .value("last")
                        .label("last")
                        .build())
                .build());

        fields.add(FormFieldText.builder()
                .id("field_405377198216737E11")
                .name("_bigText")
                .label("This is a big text")
                .type(FormFieldType.STRING)
                .placeholder("Write here your big text!")
                .required(true)
                .rows(4)
                .build());

        fields.add(FormFieldNumber.builder()
                .id("field_62054")
                .name("__unbound_field_field_62054")
                .label("Slider 0to1")
                .type(FormFieldType.SLIDER)
                .minValue(0.0)
                .maxValue(1.0)
                .multipleOf(0.1)
                .build());

        fields.add(FormField.builder()
                .id("field_027589198264173E11")
                .name("_processList")
                .label("Multiple Input")
                .type(FormFieldType.INPUT_LIST)
                .build());

        fields.add(FormField.builder()
                .id("field_3483")
                .name("_processDocumentList")
                .label("Upload a list of documents")
                .type(FormFieldType.DOCUMENT_LIST)
                .build());

        fields.add(FormFieldNumber.builder()
                .id("field_0253450468149055E12")
                .name("_sliderInteger")
                .label("Slider")
                .type(FormFieldType.SLIDER)
                .minValue(0.0)
                .maxValue(200.0)
                .multipleOf(5.0)
                .build());

        fields.add(FormFieldSelector.builder()
                .id("field_188354818614785E11")
                .name("_radioWithDefault")
                .label("My Radio Group with default value")
                .type(FormFieldType.RADIO)
                .required(true)
                .defaultValue("thirdValue")
                .option(Option.builder()
                        .value("firstValue")
                        .label("first")
                        .build())
                .option(Option.builder()
                        .value("secondValue")
                        .label("second")
                        .build())
                .option(Option.builder()
                        .value("thirdValue")
                        .label("third")
                        .build())
                .build());

        FormFieldSubForm fieldSubform = FormFieldSubForm.builder()
                .id("field_671979933751011E11")
                .name("_customObject")
                .label("Nested Form of My Object")
                .type(FormFieldType.SUBFORM)
                .formId("_customObject")
                .formType("com.myspace.forms_sample.MyObject")
                .build();

        fields.add(fieldSubform);

        fieldSubform.setForm(subform);

        fields.add(FormFieldSelector.builder()
                .id("field_145054")
                .name("_radioWithoutDefault")
                .label("My Radio Group without default value")
                .type(FormFieldType.RADIO)
                .option(Option.builder()
                        .value("myValue")
                        .label("myText")
                        .build())
                .option(Option.builder()
                        .value("anotherValue")
                        .label("anotherText")
                        .build())
                .option(Option.builder()
                        .value("what?")
                        .label("yup!")
                        .build())
                .build());

        return Form.builder()
                .id("task")
                .name("Task-taskform.frm")
                .fields(fields)
                .build();
    }

}
