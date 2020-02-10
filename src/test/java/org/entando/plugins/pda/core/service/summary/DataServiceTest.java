package org.entando.plugins.pda.core.service.summary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.entando.plugins.pda.core.utils.TestUtils.createDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.entando.plugins.pda.core.engine.FakeEngine;
import org.entando.plugins.pda.core.exception.DataTypeNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DataServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private DataService dataService;
    private List<DataRepository> dataRepositories;

    private static final String REQUESTS_DATA_TYPE = "requests";
    private static final String CASES_DATA_TYPE = "cases";

    @Before
    public void setUp() {
        dataRepositories = new ArrayList<>();
        dataRepositories.add(createDataType(REQUESTS_DATA_TYPE, new ArrayList<>()));
        dataRepositories.add(createDataType(CASES_DATA_TYPE, new ArrayList<>()));

        dataService = new DataService(dataRepositories);
    }

    @Test
    public void shouldListDataTypes() {
        // When
        List<String> expected = Arrays.asList(REQUESTS_DATA_TYPE, CASES_DATA_TYPE);
        List<String> result = dataService.listTypes(FakeEngine.TYPE);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldGetDataType() {
        //Given
        DataRepository expected = dataRepositories.get(1);

        // When
        DataRepository result = dataService.getDataRepository(FakeEngine.TYPE, CASES_DATA_TYPE);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldThrowNotFoundWhenInvalidEngine() {
        expectedException.expect(DataTypeNotFoundException.class);
        dataService.getDataRepository("invalid", CASES_DATA_TYPE);
    }

    @Test
    public void shouldThrowNotFoundWhenInvalidDataType() {
        expectedException.expect(DataTypeNotFoundException.class);
        dataService.getDataRepository(FakeEngine.TYPE, "invalid");
    }
}
