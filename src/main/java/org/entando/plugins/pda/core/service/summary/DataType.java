package org.entando.plugins.pda.core.service.summary;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.summary.PeriodicSummary;
import org.entando.plugins.pda.core.model.summary.SummaryFrequency;

public interface DataType {

    String getId();

    String getEngine();

    /* DAO Operations */
    List<PeriodicSummary> getPeriodicSummary(Connection connection, SummaryFrequency frequency, Integer periods);

}
