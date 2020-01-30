package org.entando.plugins.pda.core.model.summary;

import org.entando.plugins.pda.core.engine.Connection;

public interface SummaryType {

    Summary calculateSummary(Connection connection, FrequencyEnum frequency);

    String getEngine();

    String getId();

    String getDescription();
}
