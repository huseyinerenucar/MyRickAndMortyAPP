package com.example.rickandmorty.struct;

import com.google.gson.annotations.Expose;
import com.rickandmortyapi.Location;

import java.util.List;

public class Locations {

    @Expose(serialize = false)
    PageInfo info;

    @Expose(serialize = false)
    List<Location> results;

    public PageInfo getInfo() {
        return info;
    }

    public void setInfo(PageInfo info) {
        this.info = info;
    }

    public List<Location> getResults() {
        return results;
    }

    public void setResults(List<Location> results) {
        this.results = results;
    }
}