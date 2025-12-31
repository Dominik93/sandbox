package com.slusasrz.mavenplugins.sample;

public class AggregateConfig {

    private String inputDirectory;
    private String name;
    private int level;

    public AggregateConfig() {
    }

    public AggregateConfig(String inputDirectory, String name, int level) {
        this.inputDirectory = inputDirectory;
        this.name = name;
        this.level = level;
    }

    public String getInputDirectory() {
        return inputDirectory;
    }

    public void setInputDirectory(String inputDirectory) {
        this.inputDirectory = inputDirectory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "AggregateConfig{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
