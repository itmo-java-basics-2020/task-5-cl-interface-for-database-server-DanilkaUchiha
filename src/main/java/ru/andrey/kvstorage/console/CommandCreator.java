package ru.andrey.kvstorage.console;

public enum CommandCreator {
    CREATE_DATABASE((env, args) -> {
        if (args.length != 2) return () -> (DatabaseCommandResult) DatabaseResult.error("Не правилльная комманда");
        return new CreateDatabaseCommand(args[1], env);
    }),
    CREATE_TABLE((env, args) -> {

        if (args.length != 3) return () -> (DatabaseCommandResult) DatabaseResult.error("Не правилльная комманда");
        return new CreateTableCommand(args[1], args[2], env);
    }),
    READ_KEY((env, args) -> {

        if (args.length != 4) return () -> (DatabaseCommandResult) DatabaseResult.error("Не правилльная комманда");
        return new ReadCommand(args[1], args[2], args[3], env);
    }),
    UPDATE_KEY((env, args) -> {

        if (args.length != 5) return () -> (DatabaseCommandResult) DatabaseResult.error("Не правилльная комманда");
        return new UpdateCommand(args[1], args[2], args[3], args[4], env);
    });

    private static DatabaseCommand err = () -> (DatabaseCommandResult) DatabaseResult.error("Не правилльная комманда");
    private ICommandCreator create;

    CommandCreator(ICommandCreator create) {
        this.create = create;
    }


    public static DatabaseCommand getCommand(ExecutionEnvironment env, String[] args) {
        try {

            return CommandCreator.valueOf(args[0]).create.getCommand(env, args);
        } catch (IllegalArgumentException ex) {
            return err;
        }
    }

    public interface ICommandCreator {
        DatabaseCommand getCommand(ExecutionEnvironment env, String args[]);
    }
}