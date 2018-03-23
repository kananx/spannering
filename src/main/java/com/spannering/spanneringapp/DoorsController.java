package com.spannering.spanneringapp;

import com.spannering.data.DoorDAO;
import com.spannering.model.Door;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.List;

@RestController
public class DoorsController {

    /**
     *  This should return a list of all doors */
    @RequestMapping(value = "/api/getAllDoors", method = RequestMethod.GET)
    public String getAllDoors() {
        return "this is a static list of doors";
    }

    /**
     *  This should return a list of all doors */
    @RequestMapping(value = "/api/getAllDoorsFromDb", method = RequestMethod.GET)
    public List<Door> getAllDoorsFromDB() {
        return DoorDAO.getInstance().getAllDoors();
    }



    /**
     * - Pass in a door object and add it to the database
       - The door_id should be autogenerated. For our autogenerated keys, we use UUID
     */
    @RequestMapping(value = "/api/addDoor", method = RequestMethod.POST)
    public String add(@RequestBody String doorName){

        System.out.println("add(" + doorName + ")");
        return "123";

    }



}