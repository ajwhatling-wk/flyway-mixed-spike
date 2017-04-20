package com.workiva.db.migration;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class V1_2_0__AddSkuidColumnToProductTable implements JdbcMigration {
    private static final String SQL_ALTER_TABLE = "ALTER TABLE product ADD COLUMN skuid TEXT;";

    @Override
    public void migrate(Connection connection) throws Exception {
        try (PreparedStatement alterTable = connection.prepareStatement(SQL_ALTER_TABLE)) {
            alterTable.execute();
        }
    }
}
