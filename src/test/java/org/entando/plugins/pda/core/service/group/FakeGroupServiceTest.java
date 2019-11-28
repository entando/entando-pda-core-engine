package org.entando.plugins.pda.core.service.group;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.junit.Before;
import org.junit.Test;

public class FakeGroupServiceTest {

    private FakeGroupService groupService;

    @Before
    public void init() {
        this.groupService = new FakeGroupService();
    }

    @Test
    public void shouldListAllGroups() {
        // Given
        List<String> loadedGroups = Arrays.asList("group1", "group2", "group3");
        groupService.addGroups(null, null, loadedGroups);

        // When
        List<String> groups = groupService.list(Connection.builder().build(), null, null);

        // Then
        assertThat(groups).containsExactlyElementsOf(loadedGroups);
    }

    @Test
    public void shouldListGroupsByContainerIdAndProcessId() {
        // Given
        List<String> loadedGroups1 = Arrays.asList("group1", "group2", "group3");
        List<String> loadedGroups2 = Arrays.asList("group4", "group5", "group6");
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
