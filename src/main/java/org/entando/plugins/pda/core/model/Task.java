package org.entando.plugins.pda.core.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Date;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "The warned bug is exposure on the Builder,"
        + "however after .build() the constructor is called and vulnerability is resolved")
public class Task {

    protected String id;
    protected String name;
    protected String description;
    protected String createdBy;
    protected Date createdAt;
    protected Date dueTo;
    protected Status status;
    protected String assignee;
    protected Map<String, Object> inputData;
    protected Map<String, Object> outputData;

    @Builder(builderMethodName = "taskBuilder")
    @SuppressWarnings("PMD.ExcessiveParameterList")
    public Task(String id, String name, String description, String createdBy, Date createdAt, Date dueTo,
            Status status, String owner, Map<String, Object> inputData, Map<String, Object> outputData) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.createdAt = createdAt == null ? null : new Date(createdAt.getTime());
        this.dueTo = dueTo == null ? null : new Date(dueTo.getTime());
        this.status = status;
        this.assignee = owner;
        this.inputData = inputData;
        this.outputData = outputData;
    }

    public Date getCreatedAt() {
        return createdAt == null ? null : new Date(createdAt.getTime());
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt == null ? null : new Date(createdAt.getTime());
    }

    public Date getDueTo() {
        return dueTo == null ? null : new Date(dueTo.getTime());
    }

    public void setDueTo(Date dueTo) {
        this.dueTo = dueTo == null ? null : new Date(dueTo.getTime());
    }

    public enum Status {
        CREATED,
        ASSIGNED,
        IN_PROGRESS,
        PAUSED,
        COMPLETED,
        FAILED
    }
}
