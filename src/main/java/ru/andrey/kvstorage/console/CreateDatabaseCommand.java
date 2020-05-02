package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.console.*;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.DatabaseImplementation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateDatabaseCommand implements DatabaseCommand {
    private final String name;
    private ExecutionEnvironment env;

    public CreateDatabaseCommand(String name, ExecutionEnvironment env) {
        this.name = name;
        this.env = env;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        try {
            Files.createDirectory(Paths.get("./Database/" + name));
            env.addDatabase(new DatabaseImplementation(name));
            return DatabaseResult.succsess("");
        } catch (IOException e) {
            return DatabaseResult.error("");
        }
    }
}
