package com.workiva.db.migration;

import com.fasterxml.jackson.jr.ob.JSON;
import org.apache.commons.io.IOUtils;
import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class V1_2_1__MigrateJsonSkuidToColumn implements JdbcMigration {
    private static final String SQL_SELECT_PRODUCTS = "SELECT pid, properties FROM product;";
    private static final String SQL_UPDATE_PRODUCT = "UPDATE product SET skuid=?, properties=? WHERE pid=?;";

    public void migrate(Connection connection) throws Exception {
        List<PreparedStatement> skuidUpdates = new LinkedList<>();

        try (PreparedStatement selectProperties = connection.prepareStatement(SQL_SELECT_PRODUCTS)) {
            ResultSet resultSet = selectProperties.executeQuery();

            while (resultSet.next()) {
                int pid = resultSet.getInt("pid");
                resultSet.getBytes("properties");

                InputStream propsStream = resultSet.getBinaryStream("properties");

                // Gracefully handle rows that do not have a value for "properties".
                if (propsStream == null) {
                    continue;
                }

                String properties = IOUtils.toString(propsStream, "utf-8");
                Map<String, Object> propertiesMap = JSON.std.mapFrom(properties);

                // If the following line is underlined with red in Intellij,
                // see this SO answer: http://stackoverflow.com/a/37787156
                String skuid = propertiesMap.getOrDefault("sku_id", "").toString();
                propertiesMap.remove("sku_id");

                String updatedJson = JSON.std.asString(propertiesMap);

                PreparedStatement moveSkuid = connection.prepareStatement(SQL_UPDATE_PRODUCT);
                moveSkuid.setString(1, skuid);
                moveSkuid.setString(2, updatedJson);
                moveSkuid.setInt(3, pid);

                skuidUpdates.add(moveSkuid);
            }

            safelyExecuteUpdates(skuidUpdates);
        }
    }

    private void safelyExecuteUpdates(List<PreparedStatement> statements) throws SQLException {
        for (PreparedStatement statement : statements) {
            try {
                statement.executeUpdate();
            } finally {
                statement.close();
            }
        }
    }
}
