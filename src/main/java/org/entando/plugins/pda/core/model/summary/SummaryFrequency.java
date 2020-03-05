package org.entando.plugins.pda.core.model.summary;

import org.entando.plugins.pda.core.exception.SummaryFrequencyInvalidException;

public enum SummaryFrequency {
    DAILY,
    MONTHLY,
    ANNUALLY;

    public static SummaryFrequency from(String value) {
        try {
            return SummaryFrequency.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SummaryFrequencyInvalidException(e);
        }
    }
}
