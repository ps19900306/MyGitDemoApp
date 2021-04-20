package com.nwq.code.liferecord.data_base.bean.fulcrum;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * create by: 86136
 * create time: 2021/4/6 14:47
 * Function description:
 */
@Entity
public class FulcrumItem {

    @Id(autoincrement = true)
    private long id;

    private long TypeId;

    @NotNull
    private String name;

    @Generated(hash = 1937155040)
    public FulcrumItem(long id, long TypeId, @NotNull String name) {
        this.id = id;
        this.TypeId = TypeId;
        this.name = name;
    }

    @Generated(hash = 1442281621)
    public FulcrumItem() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTypeId() {
        return this.TypeId;
    }

    public void setTypeId(long TypeId) {
        this.TypeId = TypeId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
