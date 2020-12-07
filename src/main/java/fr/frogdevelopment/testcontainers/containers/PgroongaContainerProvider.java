package fr.frogdevelopment.testcontainers.containers;

import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.JdbcDatabaseContainerProvider;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.jdbc.ConnectionUrl;

/**
 * Factory for pgroonga container, which is PostgreSQL with groonga extension.
 * PGroonga makes PostgreSQL fast full text search platform for all languages!
 */
public class PgroongaContainerProvider extends JdbcDatabaseContainerProvider {

    private static final String NAME = "pgroonga";
    private static final String DEFAULT_TAG = "2.2.7-alpine-13-slim";
    private static final String DEFAULT_IMAGE = "groonga/pgroonga";
    public static final String USER_PARAM = "user";
    public static final String PASSWORD_PARAM = "password";

    @Override
    public boolean supports(String databaseType) {
        return databaseType.equals(NAME);
    }

    @Override
    public JdbcDatabaseContainer newInstance() {
        return newInstance(DEFAULT_TAG);
    }

    @Override
    public JdbcDatabaseContainer newInstance(String tag) {
        return new PostgreSQLContainer(DEFAULT_IMAGE + ":" + tag);
    }

    @Override
    public JdbcDatabaseContainer newInstance(ConnectionUrl connectionUrl) {
        return newInstanceFromConnectionUrl(connectionUrl, USER_PARAM, PASSWORD_PARAM);
    }
}
