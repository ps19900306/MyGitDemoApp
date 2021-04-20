package com.nwq.code.liferecord.data_base.bean.fulcrum;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * create by: 86136
 * create time: 2021/4/6 14:54
 * Function description:
 */

@Entity
public class FulcrumFactor {

    @Id(autoincrement = true)
    private long id;

    @NotNull
    private String text;

    private int position = -1;

    @Generated(hash = 1927207124)
    public FulcrumFactor(long id, @NotNull String text, int position) {
        this.id = id;
        this.text = text;
        this.position = position;
    }

    @Generated(hash = 2016289241)
    public FulcrumFactor() {
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

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
