package org.entando.plugins.pda.core.engine;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data structure for BPM connections.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Connection {

    private String name;

    private String url;

    private String username;

    private String password;

    @Default
    private Integer connectionTimeout = 60_000;

    private Map<String, String> properties;

    private String engine;

}
