package com.nwq.code.liferecord.data_base.bean.anchor;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * create by: 86136
 * create time: 2020/10/23 17:50
 * Function description:
 */
public class ImportantNodeTypeConverter implements PropertyConverter<ImportantNodeType, String> {

    @Override
    public ImportantNodeType convertToEntityProperty(String databaseValue) {
        return ImportantNodeType.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(ImportantNodeType entityProperty) {
        return entityProperty.name();
    }

}