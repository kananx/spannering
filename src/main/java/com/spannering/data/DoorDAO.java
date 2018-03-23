package com.spannering.data;

import com.google.cloud.spanner.*;
import com.spannering.model.Door;

import java.util.LinkedList;
import java.util.List;

public class DoorDAO {


    public static final String INSTANCE_ID = "test-instance";
    public static final String DATABASE_ID = "doorsdb";

    private static DoorDAO singleton = null;
    private DatabaseClient dbClient = null;

    public static DoorDAO getInstance(){
        if(singleton == null){
            singleton = new DoorDAO();
        }
        final SpannerOptions options = SpannerOptions.newBuilder().build();
        final Spanner spanner = options.getService();
        try {
            DatabaseId db = DatabaseId.of(options.getProjectId(),INSTANCE_ID, DATABASE_ID);
            // This will return the default project id based on the environment.
            String clientProject = spanner.getOptions().getProjectId();
            if (!db.getInstanceId().getProject().equals(clientProject)) {
                System.err.println("Invalid project specified. Project in the database id should match"
                        + "the project name set in the environment variable GCLOUD_PROJECT. Expected: "
                        + clientProject);
            }
            singleton.dbClient = spanner.getDatabaseClient(db);
            return singleton;
 //           final DatabaseAdminClient dbAdminClient = spanner.getDatabaseAdminClient();

        } finally {
            spanner.close();
        }

    }



    public List<Door> getAllDoors() {

        final List<Door> allDoors = new LinkedList<>();
        final Statement statement = Statement
                .newBuilder("SELECT * from doors")
                .build();

        final ResultSet resultSet = dbClient.singleUse().executeQuery(statement);
        while (resultSet.next()) {
            allDoors.add(new Door(resultSet.getLong("door_id"), resultSet.getString("name")));
        }
        return allDoors;
    }
}
