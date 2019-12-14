
package com.example.ashokaengineer.newreportsubmitmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sendreportformat {

    @SerializedName("poolId")
    @Expose
    private String poolId;
    @SerializedName("title")
    @Expose
    private String title;

    public Sendreportformat(String poolId, String title, String description) {
        this.poolId = poolId;
        this.title = title;
        this.description = description;
    }

    @SerializedName("description")
    @Expose
    private String description;

    public String getPoolId() {
        return poolId;
    }

    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
