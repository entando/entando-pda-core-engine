package org.entando.plugins.pda.core.service.summary.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.plugins.pda.core.model.summary.Summary;
import org.entando.plugins.pda.core.service.summary.DataType;
import org.entando.plugins.pda.core.service.summary.DataTypeService;
import org.entando.web.exception.BadRequestException;

@Slf4j
public abstract class AbstractSummaryProcessor implements SummaryProcessor {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final String type;
    private final DataTypeService dataTypeService;

    protected AbstractSummaryProcessor(String type, DataTypeService dataTypeService) {
        this.type = type;
        this.dataTypeService = dataTypeService;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public abstract Summary getSummary(Connection connection, String request);

    protected DataType getDataType(String engine, String dataType) {
        return dataTypeService.getDataType(engine, dataType);
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
