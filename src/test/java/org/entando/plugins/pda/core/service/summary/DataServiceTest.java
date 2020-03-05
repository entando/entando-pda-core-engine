package org.entando.plugins.pda.core.service.summary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.entando.plugins.pda.core.utils.TestUtils.createDataRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.entando.plugins.pda.core.engine.FakeEngine;
import org.entando.plugins.pda.core.exception.DataRepositoryNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DataServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private DataService dataService;
    private List<DataRepository> dataRepositories;

    private static final String REQUESTS_TYPE = "requests";
    private static final String CASES_TYPE = "cases";

    @Before
    public void setUp() {
        dataRepositories = new ArrayList<>();
        dataRepositories.add(createDataRepository(REQUESTS_TYPE, new ArrayList<>()));
        dataRepositories.add(createDataRepository(CASES_TYPE, new ArrayList<>()));

        dataService = new DataService(dataRepositories);
    }

    @Test
    public void shouldListDataRepositories() {
        // When
        List<String> expected = Arrays.asList(REQUESTS_TYPE, CASES_TYPE);
        List<String> result = dataService.listDataRepositories(FakeEngine.TYPE);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldGetDataRepository() {
        //Given
        DataRepository expected = dataRepositories.get(1);

        // When
        DataRepository result = dataService.getDataRepository(FakeEngine.TYPE, CASES_TYPE);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldThrowNotFoundWhenInvalidEngine() {
        expectedException.expect(DataRepositoryNotFoundException.class);
        dataService.getDataRepository("invalid", CASES_TYPE);
    }

    @Test
    public void shouldThrowNotFoundWhenInvalidDataRepository() {
        expectedException.expect(DataRepositoryNotFoundException.class);
        dataService.getDataRepository(FakeEngine.TYPE, "invalid");
    }
}
