package com.spannering.data;

import com.google.cloud.spanner.Database;
import com.google.cloud.spanner.DatabaseAdminClient;
import com.google.cloud.spanner.DatabaseId;
import com.google.cloud.spanner.Operation;
import com.google.spanner.admin.database.v1.CreateDatabaseMetadata;

import java.util.Arrays;

public class Initializer {

    void createDatabase(DatabaseAdminClient dbAdminClient, DatabaseId id) {
        Operation<Database, CreateDatabaseMetadata> op = dbAdminClient
                .createDatabase(
                        id.getInstanceId().getInstance(),
                        id.getDatabase(),
                        Arrays.asList(
                                "CREATE TABLE Doors (\n"
                                        + "  door_id   INT64 NOT NULL,\n"
                                        + "  name  STRING(128),\n"
                                        + ") PRIMARY KEY (door_id)"));
        Database db = op.waitFor().getResult();
        System.out.println("Created database [" + db.getId() + "]");
    }


}
