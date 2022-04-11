package org.entando.plugins.pda.core.service.summary.processor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.summary.Summary;
import org.entando.plugins.pda.core.service.summary.DataRepository;
import org.entando.plugins.pda.core.service.summary.DataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AbstractSummaryProcessorTest {

    private static final String TYPE = "Type";

    private DataRepository dataRepository;
    private DataService dataService;

    @BeforeEach
    public void setUp() {
        dataService = mock(DataService.class);
        dataRepository = mock(DataRepository.class);

        when(dataService.getDataRepository(any(), any())).thenReturn(dataRepository);
    }

    @Test
    public void shouldInitializeConstructor() {
        AbstractSummaryProcessor processor = createSummaryProcessor(TYPE, dataService);

        assertThat(processor.getType()).isEqualTo(TYPE);
        assertThat(processor.getDataRepository(null, null)).isEqualTo(dataRepository);
    }

    private static AbstractSummaryProcessor createSummaryProcessor(String type, DataService dataService) {
        return new AbstractSummaryProcessor(type, dataService) {
            @Override
            public Summary getSummary(Connection connection, String request) {
                return null;
            }
        };
    }

}
