
package com.example.ashokaengineer;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Getpoolreportformat {

    @SerializedName("reports")
    @Expose
    private List<Report> reports = null;

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

}
