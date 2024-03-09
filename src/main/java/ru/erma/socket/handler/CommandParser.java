// CommandParser.java
package ru.erma.socket.handler;

public class CommandParser {
    public Command parse(String message) {
        String[] parts = message.split(" ");
        String command = parts[0];
        String[] params = new String[parts.length - 1];
        System.arraycopy(parts, 1, params, 0, parts.length - 1);
        return new Command(command, params);
    }
}