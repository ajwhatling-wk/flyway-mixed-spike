package com.workiva.db.migration;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class V1_0_4__AddProducts implements JdbcMigration {
    private static final String SQL_INSERT_NONSENSE_PRODUCTS = "INSERT INTO product(name) VALUES " +
            "('Doge Coin Collectable')," +
            "('Tears of the Raging Call of Duty Players')," +
            "('Replica Chuck Norris Beard');";

    public void migrate(Connection connection) throws Exception {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT_NONSENSE_PRODUCTS)) {
            stmt.execute();
        }
    }
}
