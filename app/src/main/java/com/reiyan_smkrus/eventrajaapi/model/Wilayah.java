package com.reiyan_smkrus.eventrajaapi.model;
import com.google.gson.annotations.Expose;
public class Wilayah {
    @Expose
    private Integer id;
    @Expose
    private String name;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
