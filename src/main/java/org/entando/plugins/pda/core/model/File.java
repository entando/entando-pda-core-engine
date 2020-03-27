package org.entando.plugins.pda.core.model;

import java.util.Base64;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@AllArgsConstructor
public class File {
    private String type;
    private String name;
    private byte[] data;
    private Integer size = 0;


    public File(String rawData) {
        String[] split = rawData.split(";");

        for (String property : split) {
            if (property.startsWith("data:")) {
                type = property.replace("data:", "");
            } else if (property.startsWith("name=")) {
                name = property.replace("name=", "").replace("%20", " ");
            } else if (property.startsWith("base64,")) {
                data = property.replace("base64,", "").getBytes();
                size = Base64.getDecoder().decode(data).length;
            }
        }
    }
}
