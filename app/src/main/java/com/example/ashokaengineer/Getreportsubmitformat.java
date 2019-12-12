
package com.example.ashokaengineer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Getreportsubmitformat {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("report")
    @Expose
    private Report report;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

}
