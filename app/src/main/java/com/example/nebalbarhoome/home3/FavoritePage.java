package com.example.nebalbarhoome.home3;

import java.io.Serializable;

public class FavoritePage implements Serializable{

    private String name;

    private String url;

    private int visitCount;

    public String getName() {

        return name;

    }

    public String getUrl() {

        return url;

    }

    public int getVisitCount() {

        return visitCount;

    }

    public void setName(String name) {

        this.name = name;

    }

    public void setUrl(String url) {

        this.url = url;

    }

    public void setVisitCount(int visitCount) {

        this.visitCount = visitCount;

    }

    public void incrementVisitCount(){
        visitCount++;
    }

    public FavoritePage(String name, String url, int visitCount) {

        super();

        this.name = name;

        this.url = url;

        this.visitCount = visitCount;

    }

}