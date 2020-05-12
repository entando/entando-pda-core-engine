package org.entando.plugins.pda.core.service.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.ProcessInstance;
import org.entando.plugins.pda.core.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;

public class FakeProcessInstanceServiceTest {

    private static final String TEST_USER = "test";
    private static final String TEST2_USER = "test2";

    private FakeProcessInstanceService processInstanceService;

    @Before
    public void init() {
        processInstanceService = new FakeProcessInstanceService();
    }

    @Test
    public void shouldListProcessInstances() {
        // Given
        processInstanceService.initiateProcess(TEST_USER, ProcessInstance.builder().build());
        processInstanceService.initiateProcess(TEST_USER, ProcessInstance.builder().build());
        processInstanceService.initiateProcess(TEST_USER, ProcessInstance.builder().build());
        processInstanceService.initiateProcess(TEST2_USER, ProcessInstance.builder().build());
        processInstanceService.initiateProcess(TEST2_USER, ProcessInstance.builder().build());

        // When
        List<ProcessInstance> processInstanceList = processInstanceService
                .list(Connection.builder().build(), "myProcess", TestUtils.getDummyUser(TEST_USER));

        // Then
        assertThat(processInstanceList.size()).isEqualTo(3);
    }
}
