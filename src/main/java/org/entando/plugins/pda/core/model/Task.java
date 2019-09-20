package org.entando.plugins.pda.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Task {
    @JsonIgnore
    Integer getId();

    @JsonIgnore
    String getName();

    @JsonIgnore
    String getProcessId();

    @JsonIgnore
    Integer getProcessInstanceId();

    @JsonIgnore
    String getContainerId();
}
