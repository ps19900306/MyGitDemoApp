package com.nwq.code.liferecord.data_base.bean.fulcrum;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * create by: 86136
 * create time: 2021/4/6 14:51
 * Function description:
 */
@Entity
public class FulcrumType {

    @Id(autoincrement = true)
    private long id;

    private long ItemId;

    @NotNull
    private long parentId= -1;//父类ID 根节点的id是-1

    @NotNull
    private String name;

    @Generated(hash = 471554181)
    public FulcrumType(long id, long ItemId, long parentId, @NotNull String name) {
        this.id = id;
        this.ItemId = ItemId;
        this.parentId = parentId;
        this.name = name;
    }

    @Generated(hash = 558895954)
    public FulcrumType() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItemId() {
        return this.ItemId;
    }

    public void setItemId(long ItemId) {
        this.ItemId = ItemId;
    }

    public long getParentId() {
        return this.parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
