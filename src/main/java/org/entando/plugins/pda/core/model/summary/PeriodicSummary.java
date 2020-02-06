package org.entando.plugins.pda.core.model.summary;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PeriodicSummary {
    private LocalDate date;
    private Double value;
}
