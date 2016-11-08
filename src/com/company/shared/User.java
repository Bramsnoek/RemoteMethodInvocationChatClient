package com.company.shared;

import java.io.Serializable;

/**
 * Coded by Ferry on 8-11-2016.
 */
public class User implements Serializable {
    private int id;
    private String name;

    public User(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
}
