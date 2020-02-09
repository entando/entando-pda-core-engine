package org.entando.plugins.pda.core.model.summary;

import lombok.Builder;
import lombok.Data;

@Data
public class CardSummary implements Summary {
    private Double value;
    private Double percentage;

    @Builder
    public CardSummary(double value, double previousValue) {
        this.value = value;
        this.percentage = previousValue > 0 ? value / previousValue : 1;
    }
}
