package org.entando.plugins.pda.core.engine;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Connection {

    private String name;
    private String host;
    @Builder.Default
    private String port = "80";
    private String schema;
    @Builder.Default
    private String app = "/";
    private String username;
    private String password;
    @Builder.Default
    private Integer connectionTimeout = 60_000; //millis
    private Map<String, String> properties;
    private String engine;

    public String getUrl() {
        String url = String.format("%s://%s:%s%s", schema, host, port, app);

        if (url.substring(url.length() - 1).equals("/")) {
            url = url.substring(0, url.length() - 1);
        }

        return url;
    }
}
