package org.entando.plugins.pda.core.service.summary;

import com.google.common.annotations.VisibleForTesting;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.entando.plugins.pda.core.exception.DataTypeNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataService {

    private final List<DataRepository> repositories;

    public List<String> listTypes(String engine) {
        return repositories.stream()
                .filter(d -> d.getEngine().equals(engine))
                .map(DataRepository::getId)
                .collect(Collectors.toList());
    }

    public DataRepository getDataRepository(String engine, String id) {
        return repositories.stream()
                .filter(dataType -> dataType.getId().equals(id)
                        && dataType.getEngine().equals(engine))
                .findFirst()
                .orElseThrow(DataTypeNotFoundException::new);
    }

    @VisibleForTesting
    public void setRepositories(List<DataRepository> repositories) {
        this.repositories.clear();
        this.repositories.addAll(repositories);
    }

}
