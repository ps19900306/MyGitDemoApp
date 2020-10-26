package com.nwq.code.liferecord.data_base.bean;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;

/**
 * create by: 86136
 * create time: 2020/10/23 14:52
 * Function description:
 */
@Entity
public class AnchorPoint {

    @Id(autoincrement = true)
    private long id;

    @NotNull
    private String text;
    private String comment;
    private java.util.Date date;

    @Convert(converter = AnchorPointTypeConverter.class, columnType = String.class)
    private AnchorPointType type;

    @Generated(hash = 809884710)
    public AnchorPoint(long id, @NotNull String text, String comment,
                       java.util.Date date, AnchorPointType type) {
        this.id = id;
        this.text = text;
        this.comment = comment;
        this.date = date;
        this.type = type;
    }

    @Generated(hash = 1292665584)
    public AnchorPoint() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AnchorPointType getType() {
        return type;
    }

    public void setType(AnchorPointType type) {
        this.type = type;
    }
}
