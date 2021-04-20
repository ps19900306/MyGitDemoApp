package com.nwq.code.liferecord.data_base.bean.anchor;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

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

    private int position = -1;

    private java.util.Date date;

    @Convert(converter = AnchorPointTypeConverter.class, columnType = String.class)
    private AnchorPointType anchorPointType = AnchorPointType.UNDEFINED;

    @Convert(converter = ContentTypeConverter.class, columnType = String.class)
    private ContentType contentType;

    @Generated(hash = 203412522)
    public AnchorPoint(long id, @NotNull String text, String comment, int position,
                       java.util.Date date, AnchorPointType anchorPointType,
                       ContentType contentType) {
        this.id = id;
        this.text = text;
        this.comment = comment;
        this.position = position;
        this.date = date;
        this.anchorPointType = anchorPointType;
        this.contentType = contentType;
    }

    @Generated(hash = 1292665584)
    public AnchorPoint() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public java.util.Date getDate() {
        return this.date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public AnchorPointType getAnchorPointType() {
        return this.anchorPointType;
    }

    public void setAnchorPointType(AnchorPointType anchorPointType) {
        this.anchorPointType = anchorPointType;
    }

    public ContentType getContentType() {
        return this.contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

}
