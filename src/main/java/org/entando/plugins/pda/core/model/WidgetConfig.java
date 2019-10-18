package org.entando.plugins.pda.core.model;

import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.entando.plugins.pda.core.engine.Connection;

@Data
public class WidgetConfig {
    private String pageId;

    private String frameId;

    private Connection connection;

    protected Map<String,Object> extraProperties;

    @Builder
    public WidgetConfig(String pageId, String frameId, Connection connection,
            @Singular Map<String,Object> extraProperties) {
        this.pageId = pageId;
        this.frameId = frameId;
        this.connection = connection;
        this.extraProperties = extraProperties == null ? new HashMap<>() : extraProperties;
    }

    public String getKey() {
        return getKey(pageId, frameId);
    }

    public static String getKey(String pageId, String frameId) {
        return String.format("%s-%s", pageId, frameId);
    }
}
