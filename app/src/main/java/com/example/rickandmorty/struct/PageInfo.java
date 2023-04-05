package com.example.rickandmorty.struct;

import com.google.gson.annotations.Expose;

public class PageInfo {

    @Expose(serialize = false)
    int count;

    @Expose(serialize = false)
    int pages;

    @Expose(serialize = false)
    String next;

    @Expose(serialize = false)
    String prev;

    public PageInfo() {
        this.count = 0;
        this.pages = 0;
        this.next = null;
        this.prev = null;
    }

    public PageInfo(int count, int pages, String next, String prev) {
        this.count = count;
        this.pages = pages;
        this.next = next;
        this.prev = prev;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }
}
