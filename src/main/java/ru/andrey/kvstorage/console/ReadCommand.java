package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.console.*;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

public class ReadCommand implements DatabaseCommand {
    private String database;
    private String tableName;
    private String key;
    private ExecutionEnvironment env;

    public ReadCommand(String database, String tableName, String key, ExecutionEnvironment env) {
        this.database = database;
        this.tableName = tableName;
        this.key = key;
        this.env = env;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        try {
            if (env.getDatabase(database).isEmpty()) throw new DatabaseException("");
            return DatabaseResult.succsess(env.getDatabase(database).get().read(tableName, key));
        } catch (DatabaseException ex) {
            return DatabaseResult.error(ex.getMessage());
        }
    }
}
