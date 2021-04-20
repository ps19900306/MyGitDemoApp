package com.nwq.code.liferecord.data_base.bean.anchor;

import org.greenrobot.greendao.converter.PropertyConverter;

public class AnchorPointTypeConverter implements PropertyConverter<AnchorPointType, String> {

    @Override
    public AnchorPointType convertToEntityProperty(String databaseValue) {
        return AnchorPointType.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(AnchorPointType entityProperty) {
        return entityProperty.name();
    }

}