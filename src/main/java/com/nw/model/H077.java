package com.nw.model;

import java.util.Date;

public class H077 {
    private Long id;

    private Date vTime;

    private Float v;

    public H077(Long id, Date vTime, Float v) {
        this.id = id;
        this.vTime = vTime;
        this.v = v;
    }

    public H077() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getvTime() {
        return vTime;
    }

    public void setvTime(Date vTime) {
        this.vTime = vTime;
    }

    public Float getV() {
        return v;
    }

    public void setV(Float v) {
        this.v = v;
    }
}