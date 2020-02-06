package org.entando.plugins.pda.core.service.summary;

import com.google.common.annotations.VisibleForTesting;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.entando.plugins.pda.core.exception.DataTypeNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataTypeService {

    private final List<DataType> dataTypes;

    public List<DataType> listDataTypes(String engine) {
        return dataTypes.stream()
                .filter(d -> d.getEngine().equals(engine))
                .collect(Collectors.toList());
    }

    public DataType getDataType(String engine, String id) {
        return dataTypes.stream()
                .filter(dataType -> dataType.getId().equals(id)
                        && dataType.getEngine().equals(engine))
                .findFirst()
                .orElseThrow(DataTypeNotFoundException::new);
    }

    @VisibleForTesting
    public void setDataTypes(List<DataType> dataTypes) {
        this.dataTypes.clear();
        this.dataTypes.addAll(dataTypes);
    }

}
