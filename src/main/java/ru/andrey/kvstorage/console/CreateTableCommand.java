package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.console.*;
import ru.andrey.kvstorage.exception.DatabaseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateTableCommand implements DatabaseCommand {
    private String database;
    private String tableName;
    private ExecutionEnvironment env;

    public CreateTableCommand(String database, String tableName, ExecutionEnvironment env) {
        this.database = database;
        this.tableName = tableName;
        this.env = env;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        try {
            if (env.getDatabase(database).isEmpty()) throw new DatabaseException("");
            env.getDatabase(database).get().createTableIfNotExists(tableName);
            return DatabaseResult.succsess("");
        } catch (DatabaseException e) {
            return DatabaseResult.error("");
        }
    }
}
