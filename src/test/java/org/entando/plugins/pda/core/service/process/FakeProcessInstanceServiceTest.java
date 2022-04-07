package org.entando.plugins.pda.core.service.process;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.ProcessInstance;
import org.entando.plugins.pda.core.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FakeProcessInstanceServiceTest {

    private static final String MY_PROCESS = "myProcess";
    private static final String MY_PROCESS2 = "myProcess2";

    private FakeProcessInstanceService processInstanceService;

    @BeforeEach
    public void init() {
        processInstanceService = new FakeProcessInstanceService();
    }

    @Test
    public void shouldListProcessInstances() {
        // Given
        ProcessInstance processInstance1 = getProcessInstance();
        ProcessInstance processInstance2 = getProcessInstance();
        ProcessInstance processInstance3 = getProcessInstance();
        processInstanceService.initiateProcess(MY_PROCESS, processInstance1);
        processInstanceService.initiateProcess(MY_PROCESS, processInstance2);
        processInstanceService.initiateProcess(MY_PROCESS, processInstance3);
        processInstanceService.initiateProcess(MY_PROCESS2, getProcessInstance());
        processInstanceService.initiateProcess(MY_PROCESS2, getProcessInstance());

        // When
        List<ProcessInstance> processInstanceList = processInstanceService
                .list(Connection.builder().build(), MY_PROCESS, TestUtils.getDummyUser("test"));

        // Then
        assertThat(processInstanceList).containsExactlyInAnyOrder(processInstance1, processInstance2, processInstance3);
    }

    private ProcessInstance getProcessInstance() {
        return ProcessInstance.builder()
                .id(randomNumeric(10))
                .state(randomNumeric(1))
                .processDefinitionId(random(10))
                .processName(random(10))
                .initiator(random(10))
                .processVersion(random(5))
                .date(LocalDateTime.now())
                .build();
    }
}
