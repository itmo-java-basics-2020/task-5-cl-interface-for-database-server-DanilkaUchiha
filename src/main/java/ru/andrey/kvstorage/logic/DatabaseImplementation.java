package ru.andrey.kvstorage.logic;

import ru.andrey.kvstorage.exception.DatabaseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class DatabaseImplementation implements Database {

    private String name;

    public DatabaseImplementation(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void createTableIfNotExists(String tableName) throws DatabaseException {
        try {
            Files.createDirectories(Paths.get("./Database/" + name + "/" + tableName));
        } catch (IOException e) {
            throw new DatabaseException("");
        }
    }

    @Override
    public void createTableIfNotExists(String tableName, int segmentSizeInBytes) throws DatabaseException {

    }

    @Override
    public void write(String tableName, String objectKey, String objectValue) throws DatabaseException {
        File file = new File("./Database/" + name + "/" + tableName + "/" + objectKey);
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(file));
            writer.print(objectValue);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public String read(String tableName, String objectKey) throws DatabaseException {
        try {
            return new String(Files.readAllBytes(Paths.get("./Database/" + name + "/" + tableName + "/" + objectKey)));
        } catch (IOException e) {
            throw new DatabaseException("");
        }
    }
}
