package com.spannering.data;

import com.google.cloud.spanner.*;
import com.spannering.model.Door;

import javax.validation.constraints.Null;
import java.util.*;

public class DoorDAO {

    private static final Statement GET_ALL_DOORS_STATEMENT = Statement
            .newBuilder("SELECT * from doors order by name  ")
            .build();

    public List<Door> getAllDoors(final DatabaseClient dbClient) {
        final List<Door> allDoors = new LinkedList<>();
        System.out.println("executeQuery " + GET_ALL_DOORS_STATEMENT);
        final ReadContext readContext = dbClient.singleUse();
        final ResultSet resultSet = readContext.executeQuery(GET_ALL_DOORS_STATEMENT);
        while (resultSet.next()) {
            allDoors.add(new Door(resultSet.getLong("door_id"), resultSet.getString("name")));
        }
        System.out.println("returned " + allDoors.size());
        return allDoors;
    }

    public Object deleteAllDoors(final DatabaseClient dbClient){

        dbClient.write(Collections.singletonList(Mutation.delete("doors", KeySet.all())));
        return "";  //TODO: return 204 (no content) and no body

    }




    public Door addDoor(final DatabaseClient dbClient, final Door newDoor) {

        dbClient.write(Collections.singletonList(Mutation.newInsertBuilder("doors")
                .set("door_id").to(newDoor.getId())
                .set("name").to(newDoor.getName())
                .build()
        ));

        return newDoor;

    }


}
