package org.entando.plugins.pda.core.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.entando.keycloak.security.AuthenticatedUser;
import org.entando.plugins.pda.core.engine.Connection;
import org.entando.web.exception.InternalServerException;
import org.keycloak.representations.AccessToken;
import org.springframework.core.io.ClassPathResource;

public abstract class TestUtils {

    public static final String TASK_ID_1 = "1";
    public static final String TASK_NAME_1 = "Task 1";
    public static final String TASK_SUBJECT_1 = "Task Subject 1";
    public static final String TASK_COMMENT_ID_1_1 = "t1-c1";
    public static final String TASK_COMMENT_1_1 = "This is a task comment!";
    public static final String TASK_COMMENT_ID_1_2 = "t1-c2";
    public static final String TASK_COMMENT_1_2 = "Whatever he said...";

    public static final String TASK_ID_2 = "2";
    public static final String TASK_NAME_2 = "Task 2";
    public static final String TASK_SUBJECT_2 = "Task Subject 2";
    public static final String TASK_COMMENT_ID_2_1 = "t2-c1";
    public static final String TASK_COMMENT_2_1 = "This is another task comment!";

    public static final String PROCESS_ID_1 = "1";
    public static final String PROCESS_NAME_1 = "Process 1";

    public static final String PROCESS_ID_2 = "2";
    public static final String PROCESS_NAME_2 = "Process 2";

    public static AuthenticatedUser getDummyUser() {
        return getDummyUser("test");
    }

    public static AuthenticatedUser getDummyUser(String username) {
        AccessToken token = new AccessToken();
        token.setPreferredUsername(username);
        return new AuthenticatedUser(username, token);
    }

    public static Connection getDummyConnection() {
        return Connection.builder()
                .username("myUsername")
                .password("myPassword")
                .schema("http")
                .host("myurl")
                .port("8080")
                .build();
    }

    public static String readFromFile(String filename) {
        try (InputStream is = new ClassPathResource(filename).getInputStream()){
            return IOUtils.toString(is);
        } catch (IOException e) {
            throw new InternalServerException("Error reading file", e);
        }
    }

    public static String minifyJsonString(String prettyJson) throws IOException {
        return new ObjectMapper().readValue(prettyJson, JsonNode.class).toString();
    }

}
