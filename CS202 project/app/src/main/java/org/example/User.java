package org.example;

import java.sql.Connection;

public abstract class User {
    protected final Connection connection;

    public User(Connection connection) {
        this.connection = connection;
    }

    public abstract void handleActions();
}