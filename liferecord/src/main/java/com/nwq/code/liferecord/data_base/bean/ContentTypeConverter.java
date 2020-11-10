package com.nwq.code.liferecord.data_base.bean;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * create by: 86136
 * create time: 2020/10/29 14:53
 * Function description:
 */
public class ContentTypeConverter implements PropertyConverter<ContentType, String> {

    @Override
    public ContentType convertToEntityProperty(String databaseValue) {
        return ContentType.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(ContentType entityProperty) {
        return entityProperty.name();
    }

}
