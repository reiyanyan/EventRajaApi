package com.reiyan_smkrus.eventrajaapi.model;
import com.google.gson.annotations.Expose;
import java.util.List;
public class RajaApi {
    @Expose
    private List<Wilayah> data = null;
    public List<Wilayah> getData() { return data; }
    public void setData(List<Wilayah> data) { this.data = data; }
}
