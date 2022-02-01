package com.jithu.newone.model;

public class ParseItemModel {
    String name,rating,close,direction;

    public ParseItemModel(String name, String rating) {
        this.name = name;
        this.rating = rating;

    }

    public ParseItemModel(String name, String rating, String close) {
        this.name = name;
        this.rating = rating;
        this.close = close;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}

