package org.entando.plugins.pda.core.service.summary.processor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.summary.Summary;
import org.entando.plugins.pda.core.service.summary.DataType;
import org.entando.plugins.pda.core.service.summary.DataTypeService;
import org.junit.Before;
import org.junit.Test;

public class AbstractSummaryProcessorTest {
    private static final String TYPE = "Type";

    private DataType dataType;
    private DataTypeService dataTypeService;

    @Before
    public void setUp() {
        dataTypeService = mock(DataTypeService.class);
        dataType = mock(DataType.class);

        when(dataTypeService.getDataType(any(), any())).thenReturn(dataType);
    }

    @Test
    public void shouldInitializeConstructor() {
        AbstractSummaryProcessor processor = createSummaryProcessor(TYPE, dataTypeService);

        assertThat(processor.getType()).isEqualTo(TYPE);
        assertThat(processor.getDataType(null, null)).isEqualTo(dataType);
    }

    private static AbstractSummaryProcessor createSummaryProcessor(String type, DataTypeService dataTypeService) {
        return new AbstractSummaryProcessor(type, dataTypeService) {
            @Override
            public Summary getSummary(Connection connection, String request) {
                return null;
            }
        };
    }

}
