package com.arbud.aplikasisqlite.model;

public class Data {
    private String id, name, addrees;

    public Data() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddrees() {
        return addrees;
    }

    public void setAddrees(String addrees) {
        this.addrees = addrees;
    }

    public Data(String id, String name, String addrees) {
        this.id = id;
        this.name = name;
        this.addrees = addrees;
    }
}
