package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.logic.Database;

import java.util.HashMap;
import java.util.Optional;

public class DatabaseEnvironment implements ExecutionEnvironment {
    private HashMap<String, Database> db;

    @Override
    public Optional<Database> getDatabase(String name) {
        return Optional.of(db.get(name));
    }

    @Override
    public void addDatabase(Database db) {
        this.db.put(db.getName(), db);
    }
}
