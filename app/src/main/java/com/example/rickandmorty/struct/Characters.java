package com.example.rickandmorty.struct;

import com.google.gson.annotations.Expose;
import com.rickandmortyapi.Character;

import java.util.List;

public class Characters {

    @Expose(serialize = false)
    PageInfo info;

    @Expose(serialize = false)
    List<Character> results;

    public PageInfo getInfo() {
        return info;
    }

    public void setInfo(PageInfo info) {
        this.info = info;
    }

    public List<Character> getResults() {
        return results;
    }

    public void setResults(List<Character> results) {
        this.results = results;
    }
}