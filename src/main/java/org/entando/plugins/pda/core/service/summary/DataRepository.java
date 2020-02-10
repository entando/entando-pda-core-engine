package org.entando.plugins.pda.core.service.summary;

import java.util.List;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.summary.PeriodicData;
import org.entando.plugins.pda.core.model.summary.SummaryFrequency;

public interface DataRepository {

    String getId();

    String getEngine();

    /* DAO Operations */
    List<PeriodicData> getPeriodicData(Connection connection, SummaryFrequency frequency, Integer periods);

}
