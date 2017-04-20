package com.workiva.db.migration;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class V1_0_3__ProductTable implements JdbcMigration {
    private static final String SQL_CREATE_TABLE = "CREATE TABLE product ("
            + "pid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + "name VARCHAR(255) NOT NULL"
            + ");";

    public void migrate(Connection connection) throws Exception {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_CREATE_TABLE)) {
            stmt.execute();
        }
    }
}
