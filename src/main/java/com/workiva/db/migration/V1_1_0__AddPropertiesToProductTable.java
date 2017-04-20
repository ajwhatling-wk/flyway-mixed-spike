package com.workiva.db.migration;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class V1_1_0__AddPropertiesToProductTable implements JdbcMigration {
    private static final String SQL_ALTER_TABLE = "ALTER TABLE product ADD COLUMN properties BLOB";

    public void migrate(Connection connection) throws Exception {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_ALTER_TABLE)) {
            stmt.execute();
        }
    }
}
