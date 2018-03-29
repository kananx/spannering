package com.spannering.data;

import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.DatabaseId;
import com.google.cloud.spanner.Spanner;
import com.google.cloud.spanner.SpannerOptions;
import com.spannering.model.Door;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class SpannerRunner {

    public static final Function<DatabaseClient, Object> GET_ALL_DOORS = new DoorDAO()::getAllDoors;
    public static final Function<DatabaseClient, Object> DELETE_ALL_DOORS = new DoorDAO()::deleteAllDoors;

    public static Function<DatabaseClient, Object> createAddDoor(final Door door){
       return dbClient -> new DoorDAO().addDoor(dbClient, door);
    }


    //TODO: externalize
    private static final String INSTANCE_ID = "test-instance";
    private static final String DATABASE_ID = "doorsdb";

    public static Object run(final Function<DatabaseClient, Object> function) {

        final SpannerOptions options = SpannerOptions.newBuilder().build();
        final Spanner spanner = options.getService();
        try {
            final DatabaseId db = DatabaseId.of(options.getProjectId(), INSTANCE_ID, DATABASE_ID);
            // This will return the default project id based on the environment.
            final String clientProject = spanner.getOptions().getProjectId();

            System.out.println("clientProject   : " + clientProject);

            if (!db.getInstanceId().getProject().equals(clientProject)) {
                System.err.println("Invalid project specified. Project in the database id should match"
                        + "the project name set in the environment variable GCLOUD_PROJECT. Expected: "
                        + clientProject);
            }
            return function.apply(spanner.getDatabaseClient(db));

        } finally {
            spanner.close();
        }

    }







}
