package com.workiva.db.migration;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class V1_1_1__AddProducts implements JdbcMigration {
    private static final String SQL_INSERT_NONSENSE_PRODUCTS = "INSERT INTO product (name, properties) VALUES " +
            "('Pokemon Go - Old People Edition', '{\"sku_id\": \"S-162345\"}'), " +
            "('Fake News Generator', '{\"sku_id\": \"S-2200932\"}'), " +
            "('Glass of Water', '{\"sku_id\": \"M-0011151\"}')";

    public void migrate(Connection connection) throws Exception {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT_NONSENSE_PRODUCTS)) {
            stmt.execute();
        }
    }
}
