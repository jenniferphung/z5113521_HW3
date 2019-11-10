package com.example.z5113521_hw3;

import android.os.Parcelable;

import java.io.Serializable;

public class Weight implements Serializable {
    public String imperial;
    public String metric;

    public Weight() {
    }

    public String getImperial() {
        return imperial;
    }

    public void setImperial(String imperial) {
        this.imperial = imperial;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
}
