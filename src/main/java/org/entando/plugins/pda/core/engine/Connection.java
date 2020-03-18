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
    private String url;
    private String username;
    private String password;
    private Integer connectionTimeout;
    private Map<String, String> properties;
    private String engine;

}
