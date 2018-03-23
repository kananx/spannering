package com.spannering.model;

public class Door {

    private final long id;
    private final String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Door(final long id, final String name) {
        this.id = id;
        this.name = name;
    }
}
