package org.entando.plugins.pda.core.service.group;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.junit.Before;
import org.junit.Test;

public class FakeGroupServiceTest {

    private static final String GROUP_1 = "group1";
    private static final String GROUP_2 = "group2";
    private static final String GROUP_3 = "group3";
    private static final String GROUP_4 = "group4";
    private static final String GROUP_5 = "group5";
    private static final String GROUP_6 = "group6";
    
    private FakeGroupService groupService;

    @Before
    public void init() {
        this.groupService = new FakeGroupService();
    }

    @Test
    public void shouldListAllGroups() {
        // Given
        List<String> loadedGroups = Arrays.asList(GROUP_1, GROUP_2, GROUP_3);
        groupService.addGroups(null, null, loadedGroups);

        // When
        List<String> groups = groupService.list(Connection.builder().build(), null, null);

        // Then
        assertThat(groups).containsExactlyElementsOf(loadedGroups);
    }

    @Test
    public void shouldListGroupsByContainerIdAndProcessId() {
        // Given
        List<String> loadedGroups1 = Arrays.asList(GROUP_1, GROUP_2, GROUP_3);
        List<String> loadedGroups2 = Arrays.asList(GROUP_4, GROUP_5, GROUP_6);
        String first = "1";
        String second = "2";
        groupService.addGroups(first, first, loadedGroups1);
        groupService.addGroups(second, second, loadedGroups2);

        // When
        List<String> groups = groupService.list(Connection.builder().build(), first, first);

        // Then
        assertThat(groups).containsExactlyElementsOf(loadedGroups1);
        assertThat(groups).doesNotContainAnyElementsOf(loadedGroups2);
    }
}
