package com.nw.model;

import java.util.Date;

public class ULog {
    private Long id;

    private Long lUserId;

    private Date lLogTime;

    private Integer lLoginorlogout;

    private String lNote;

    public ULog(Long id, Long lUserId, Date lLogTime, Integer lLoginorlogout, String lNote) {
        this.id = id;
        this.lUserId = lUserId;
        this.lLogTime = lLogTime;
        this.lLoginorlogout = lLoginorlogout;
        this.lNote = lNote;
    }

    public ULog() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getlUserId() {
        return lUserId;
    }

    public void setlUserId(Long lUserId) {
        this.lUserId = lUserId;
    }

    public Date getlLogTime() {
        return lLogTime;
    }

    public void setlLogTime(Date lLogTime) {
        this.lLogTime = lLogTime;
    }

    public Integer getlLoginorlogout() {
        return lLoginorlogout;
    }

    public void setlLoginorlogout(Integer lLoginorlogout) {
        this.lLoginorlogout = lLoginorlogout;
    }

    public String getlNote() {
        return lNote;
    }

    public void setlNote(String lNote) {
        this.lNote = lNote == null ? null : lNote.trim();
    }
}