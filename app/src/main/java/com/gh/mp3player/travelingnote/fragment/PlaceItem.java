package com.gh.mp3player.travelingnote.fragment;

public class PlaceItem {
    private String name,desc, linkPhoTo;
    private double lat,log;

    public PlaceItem(String name, String desc, String linkPhoTo, double lat, double log) {
        this.name = name;
        this.desc = desc;
        this.linkPhoTo = linkPhoTo;
        this.lat = lat;
        this.log = log;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLinkPhoTo() {
        return linkPhoTo;
    }

    public void setLinkPhoTo(String linkPhoTo) {
        this.linkPhoTo = linkPhoTo;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }
}
