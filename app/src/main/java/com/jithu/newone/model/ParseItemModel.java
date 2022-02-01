package com.jithu.newone.model;

public class ParseItemModel {
    String name,rating,close;

    public ParseItemModel(String name, String rating) {
        this.name = name;
        this.rating = rating;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }
}

