package com.nwq.code.liferecord.data_base.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import java.util.Date;

/**
 * create by: 86136
 * create time: 2020/10/24 10:06
 * Function description:
 */
@Entity
public class TakeTimeRecord {

    @Id(autoincrement = true)
    private long id;
    private long nodeId;

    private java.util.Date startDate;
    private java.util.Date endTime;
    @Generated(hash = 1789738678)
    public TakeTimeRecord(long id, long nodeId, java.util.Date startDate,
            java.util.Date endTime) {
        this.id = id;
        this.nodeId = nodeId;
        this.startDate = startDate;
        this.endTime = endTime;
    }

    @Generated(hash = 630057646)
    public TakeTimeRecord() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public java.util.Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    public java.util.Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

}
