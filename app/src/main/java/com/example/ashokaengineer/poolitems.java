package com.example.ashokaengineer;

public class poolitems {


    private String poolid;
    private String poolname;
    private String totalivestment;
    private String location;
    private String Report;
    private String description;

    public poolitems(String poolid, String poolname, String totalivestment, String location, String report, String description, String engineerid) {
        this.poolid = poolid;
        this.poolname = poolname;
        this.totalivestment = totalivestment;
        this.location = location;
        Report = report;
        this.description = description;
        this.engineerid = engineerid;
    }

    public String getPoolid() {
        return poolid;
    }

    public void setPoolid(String poolid) {
        this.poolid = poolid;
    }

    public String getPoolname() {
        return poolname;
    }

    public void setPoolname(String poolname) {
        this.poolname = poolname;
    }

    public String getTotalivestment() {
        return totalivestment;
    }

    public void setTotalivestment(String totalivestment) {
        this.totalivestment = totalivestment;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReport() {
        return Report;
    }

    public void setReport(String report) {
        Report = report;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEngineerid() {
        return engineerid;
    }

    public void setEngineerid(String engineerid) {
        this.engineerid = engineerid;
    }

    private String engineerid;
    }






