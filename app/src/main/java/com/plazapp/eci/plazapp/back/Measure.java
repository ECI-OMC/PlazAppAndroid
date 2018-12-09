package com.plazapp.eci.plazapp.back;

/**
 * Created by Jeffer on 8/12/2018.
 */

public class Measure {

    private String name;
    private String prefix;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Measure(String name, String prefix) {

        this.name = name;
        this.prefix = prefix;
    }
}
