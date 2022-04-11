package org.entando.plugins.pda.core.service.group;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FakeGroupServiceTest {

    private static final String GROUP_1 = "group1";
    private static final String GROUP_2 = "group2";
    private static final String GROUP_3 = "group3";
    private static final String GROUP_4 = "group4";
    private static final String GROUP_5 = "group5";
    private static final String GROUP_6 = "group6";
    private static final String PROCESS_ID_1 = "1";
    private static final String PROCESS_ID_2 = "2";

    private FakeGroupService groupService;

    @BeforeEach
    public void init() {
        this.groupService = new FakeGroupService();
    }

    @Test
    public void shouldListAllGroups() {
        // Given
        List<String> loadedGroups1 = Arrays.asList(GROUP_1, GROUP_2, GROUP_3);
        List<String> loadedGroups2 = Arrays.asList(GROUP_4, GROUP_5, GROUP_6);
        groupService.addGroups(PROCESS_ID_1, loadedGroups1);
        groupService.addGroups(PROCESS_ID_2, loadedGroups2);

        // When
        List<String> groups = groupService.list(Connection.builder().build(), null);

        // Then
        assertThat(groups)
                .containsExactlyInAnyOrder(GROUP_1, GROUP_2, GROUP_3, GROUP_4, GROUP_5, GROUP_6);
    }

    @Test
    public void shouldListGroupsByContainerIdAndProcessId() {
        // Given
        List<String> loadedGroups1 = Arrays.asList(GROUP_1, GROUP_2, GROUP_3);
        List<String> loadedGroups2 = Arrays.asList(GROUP_4, GROUP_5, GROUP_6);
        groupService.addGroups(PROCESS_ID_1, loadedGroups1);
        groupService.addGroups(PROCESS_ID_2, loadedGroups2);

        // When
        List<String> groups = groupService.list(Connection.builder().build(), PROCESS_ID_1);

        // Then
        assertThat(groups).containsExactlyElementsOf(loadedGroups1);
        assertThat(groups).doesNotContainAnyElementsOf(loadedGroups2);
    }
}
