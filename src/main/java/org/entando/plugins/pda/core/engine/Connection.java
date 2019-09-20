package org.entando.plugins.pda.core.engine;

import java.util.Map;
import java.util.Optional;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Connection {
    private String name;
    private String host;
    private String port = "80";
    private String schema;
    private String username;
    private String password;
    private Integer connectionTimeout = 60_000; //millis
    private Map<String,String> properties;
    private String engine;

    @Builder
    public Connection(String name, String host, String port, String schema, String username, String password,
            Integer connectionTimeout, Map<String,String> properties, String engine) {
        this.name = name;
        this.host = host;
        this.port = Optional.ofNullable(port).filter(p -> !p.trim().isEmpty()).orElse(this.port);
        this.schema = schema;
        this.username = username;
        this.password = password;
        this.connectionTimeout = Optional.ofNullable(connectionTimeout).orElse(this.connectionTimeout);
        this.properties = properties;
        this.engine = engine;
    }

    public String getUrl() {
        return String.format("%s://%s:%s", schema, host, port);
    }
}
