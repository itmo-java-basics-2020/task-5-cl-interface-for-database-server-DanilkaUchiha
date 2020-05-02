package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.console.*;
import ru.andrey.kvstorage.exception.DatabaseException;

public class UpdateCommand implements DatabaseCommand {
    private String database;
    private String tableName;
    private String key;
    private String value;
    private ExecutionEnvironment env;

    public UpdateCommand(String database, String tableName, String key, String value, ExecutionEnvironment env) {
        this.database = database;
        this.tableName = tableName;
        this.key = key;
        this.value = value;
        this.env = env;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        try {
            if (env.getDatabase(database).isEmpty()) throw new DatabaseException("");
            env.getDatabase(database).get().write(tableName, key, value);
            return DatabaseResult.succsess("");
        } catch (DatabaseException ex) {
            return DatabaseResult.error(ex.getMessage());
        }
    }
}
