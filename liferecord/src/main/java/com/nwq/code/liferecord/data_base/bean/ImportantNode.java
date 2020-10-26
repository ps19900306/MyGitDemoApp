package com.nwq.code.liferecord.data_base.bean;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import java.util.Date;

/**
 * create by: 86136
 * create time: 2020/10/23 17:47
 * Function description:
 * 事情必须有截至日期
 */
@Entity
public class ImportantNode {

    @Id(autoincrement = true)
    private long id;

    @NotNull
    private String name;
    private String comment;

    @Convert(converter = ImportantNodeTypeConverter.class, columnType = String.class)
    private ImportantNodeType type;

    private java.util.Date startDate;
    private java.util.Date closeDate;

    private Long costTime;

    @Generated(hash = 1736669731)
    public ImportantNode(long id, @NotNull String name, String comment,
            ImportantNodeType type, java.util.Date startDate, java.util.Date closeDate,
            Long costTime) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.type = type;
        this.startDate = startDate;
        this.closeDate = closeDate;
        this.costTime = costTime;
    }

    @Generated(hash = 752636331)
    public ImportantNode() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ImportantNodeType getType() {
        return this.type;
    }

    public void setType(ImportantNodeType type) {
        this.type = type;
    }

    public java.util.Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    public java.util.Date getCloseDate() {
        return this.closeDate;
    }

    public void setCloseDate(java.util.Date closeDate) {
        this.closeDate = closeDate;
    }

    public Long getCostTime() {
        return this.costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }


}
