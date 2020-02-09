package org.entando.plugins.pda.core.service.summary.processor;

import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.summary.Summary;

public interface SummaryProcessor {

    String getType();

    Summary getSummary(Connection connection, String request);
}
