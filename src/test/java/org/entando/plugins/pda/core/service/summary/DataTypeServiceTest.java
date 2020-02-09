package org.entando.plugins.pda.core.service.summary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.entando.plugins.pda.core.utils.TestUtils.createDataType;

import java.util.ArrayList;
import java.util.List;
import org.entando.plugins.pda.core.engine.FakeEngine;
import org.entando.plugins.pda.core.exception.DataTypeNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DataTypeServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private DataTypeService dataTypeService;
    private List<DataType> dataTypes;

    private static final String REQUESTS_DATA_TYPE = "requests";
    private static final String CASES_DATA_TYPE = "cases";

    @Before
    public void setUp() {
        dataTypes = new ArrayList<>();
        dataTypes.add(createDataType(REQUESTS_DATA_TYPE, new ArrayList<>()));
        dataTypes.add(createDataType(CASES_DATA_TYPE, new ArrayList<>()));

        dataTypeService = new DataTypeService(dataTypes);
    }

    @Test
    public void shouldListDataTypes() {
        // When
        List<DataType> result = dataTypeService.listDataTypes(FakeEngine.TYPE);

        // Then
        assertThat(result.size()).isEqualTo(dataTypes.size());
        assertThat(result).isEqualTo(dataTypes);
    }

    @Test
    public void shouldGetDataType() {
        //Given
        DataType expected = dataTypes.get(1);

        // When
        DataType result = dataTypeService.getDataType(FakeEngine.TYPE, CASES_DATA_TYPE);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldThrowNotFoundWhenInvalidEngine() {
        expectedException.expect(DataTypeNotFoundException.class);
        dataTypeService.getDataType("invalid", CASES_DATA_TYPE);
    }

    @Test
    public void shouldThrowNotFoundWhenInvalidDataType() {
        expectedException.expect(DataTypeNotFoundException.class);
        dataTypeService.getDataType(FakeEngine.TYPE, "invalid");
    }
}
