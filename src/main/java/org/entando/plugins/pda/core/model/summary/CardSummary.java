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
        if (value == previousValue) {
            this.percentage = 0.0;
        } else {
            this.percentage = previousValue > 0 ? value / previousValue - 1 : 1;
        }
    }
}
