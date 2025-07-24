package org.practice.lld.foodordersystem;

public class CommandWithTimeStamp {

    private final int timestamp;
    private final String command;

    public CommandWithTimeStamp(int timestamp, String command) {
        this.timestamp = timestamp;
        this.command = command;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getCommand() {
        return command;
    }
}