package org.entando.plugins.pda.core.engine;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.Test;

public class ConnectionTest {

    private static final String TEST_URL = "http://localhost:8080/test";

    @Test
    public void shouldReturnCorrectUrl() {
        Connection connection = getConnection(); // NOPMD

        assertThat(connection.getUrl()).isEqualTo(TEST_URL);
    }

    @Test
    public void shouldRemoveSlashAtTheEnd() {
        Connection connection = getConnection(); // NOPMD
        connection.setApp("/test/");

        assertThat(connection.getUrl()).isEqualTo(TEST_URL);
    }

    private Connection getConnection() {
        return Connection.builder()
                .schema("http")
                .host("localhost")
                .port("8080")
                .app("/test")
                .build();
    }
}
