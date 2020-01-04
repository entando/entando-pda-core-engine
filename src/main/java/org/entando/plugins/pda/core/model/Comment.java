package org.entando.plugins.pda.core.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "The warned bug is exposure on the Builder,"
        + "however after .build() the constructor is called and vulnerability is resolved")
public class Comment {

    private String id;

    private String text;

    private String createdBy;

    private Date createdAt;

    @Builder
    public Comment(String id, String text, String createdBy, Date createdAt) {
        this.id = id;
        this.text = text;
        this.createdBy = createdBy;
        this.createdAt = createdAt == null ? null : (Date) createdAt.clone();
    }

    public Date getCreatedAt() {
        return createdAt == null ? null : new Date(createdAt.getTime());
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt == null ? null : new Date(createdAt.getTime());
    }
}

