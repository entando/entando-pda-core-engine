package org.entando.plugins.pda.core.model.summary;

import org.entando.plugins.pda.core.engine.Connection;

public interface Summary {

    SummaryValue calculateSummary(Connection connection, FrequencyEnum frequency);

    SummaryType getSummaryType();

    String getEngine();

    String getId();

    String getDescription();
}
