package org.entando.plugins.pda.core.service.summary.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.exception.BadRequestException;
import org.entando.plugins.pda.core.model.summary.Summary;
import org.entando.plugins.pda.core.service.summary.DataRepository;
import org.entando.plugins.pda.core.service.summary.DataService;

@Slf4j
public abstract class AbstractSummaryProcessor implements SummaryProcessor {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final String type;
    private final DataService dataService;

    protected AbstractSummaryProcessor(String type, DataService dataService) {
        this.type = type;
        this.dataService = dataService;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public abstract Summary getSummary(Connection connection, String request);

    protected DataRepository getDataRepository(String engine, String type) {
        return dataService.getDataRepository(engine, type);
    }

    protected <T> T convertRequestObject(String request, Class<T> objectType) {
        try {
            return MAPPER.readValue(request, objectType);
        } catch (IOException e) {
            log.error("Error reading summary type request object with type {}", objectType, e);
            throw new BadRequestException(e);
        }
    }
}
