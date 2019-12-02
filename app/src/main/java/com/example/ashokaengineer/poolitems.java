package com.example.ashokaengineer;

public class poolitems {

    public String getmImageurl() {
        return mImageurl;
    }

    public void setmImageurl(String mImageurl) {
        this.mImageurl = mImageurl;
    }

    public String getPoolname() {
        return poolname;
    }

    public void setPoolname(String poolname) {
        this.poolname = poolname;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public poolitems(String mImageurl, String poolname, String area, String location) {
        this.mImageurl = mImageurl;
        this.poolname = poolname;
        this.area = area;
        this.location = location;
    }

    private String mImageurl;
    private String poolname;
    private String area;
    private String location;



}
