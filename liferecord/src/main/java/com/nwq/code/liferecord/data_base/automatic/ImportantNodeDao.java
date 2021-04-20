package com.nwq.code.liferecord.data_base.automatic;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.nwq.code.liferecord.data_base.bean.anchor.ImportantNodeType;
import com.nwq.code.liferecord.data_base.bean.anchor.ImportantNodeTypeConverter;

import com.nwq.code.liferecord.data_base.bean.anchor.ImportantNode;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "IMPORTANT_NODE".
*/
public class ImportantNodeDao extends AbstractDao<ImportantNode, Long> {

    public static final String TABLENAME = "IMPORTANT_NODE";

    /**
     * Properties of entity ImportantNode.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Comment = new Property(2, String.class, "comment", false, "COMMENT");
        public final static Property Type = new Property(3, String.class, "type", false, "TYPE");
        public final static Property StartDate = new Property(4, java.util.Date.class, "startDate", false, "START_DATE");
        public final static Property CloseDate = new Property(5, java.util.Date.class, "closeDate", false, "CLOSE_DATE");
        public final static Property CostTime = new Property(6, Long.class, "costTime", false, "COST_TIME");
    }

    private final ImportantNodeTypeConverter typeConverter = new ImportantNodeTypeConverter();

    public ImportantNodeDao(DaoConfig config) {
        super(config);
    }
    
    public ImportantNodeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"IMPORTANT_NODE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: id
                "\"NAME\" TEXT NOT NULL ," + // 1: name
                "\"COMMENT\" TEXT," + // 2: comment
                "\"TYPE\" TEXT," + // 3: type
                "\"START_DATE\" INTEGER," + // 4: startDate
                "\"CLOSE_DATE\" INTEGER," + // 5: closeDate
                "\"COST_TIME\" INTEGER);"); // 6: costTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"IMPORTANT_NODE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ImportantNode entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindString(2, entity.getName());
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(3, comment);
        }
 
        ImportantNodeType type = entity.getType();
        if (type != null) {
            stmt.bindString(4, typeConverter.convertToDatabaseValue(type));
        }
 
        java.util.Date startDate = entity.getStartDate();
        if (startDate != null) {
            stmt.bindLong(5, startDate.getTime());
        }
 
        java.util.Date closeDate = entity.getCloseDate();
        if (closeDate != null) {
            stmt.bindLong(6, closeDate.getTime());
        }
 
        Long costTime = entity.getCostTime();
        if (costTime != null) {
            stmt.bindLong(7, costTime);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ImportantNode entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindString(2, entity.getName());
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(3, comment);
        }
 
        ImportantNodeType type = entity.getType();
        if (type != null) {
            stmt.bindString(4, typeConverter.convertToDatabaseValue(type));
        }
 
        java.util.Date startDate = entity.getStartDate();
        if (startDate != null) {
            stmt.bindLong(5, startDate.getTime());
        }
 
        java.util.Date closeDate = entity.getCloseDate();
        if (closeDate != null) {
            stmt.bindLong(6, closeDate.getTime());
        }
 
        Long costTime = entity.getCostTime();
        if (costTime != null) {
            stmt.bindLong(7, costTime);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public ImportantNode readEntity(Cursor cursor, int offset) {
        ImportantNode entity = new ImportantNode( //
            cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // comment
            cursor.isNull(offset + 3) ? null : typeConverter.convertToEntityProperty(cursor.getString(offset + 3)), // type
            cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)), // startDate
            cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)), // closeDate
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6) // costTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ImportantNode entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setComment(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setType(cursor.isNull(offset + 3) ? null : typeConverter.convertToEntityProperty(cursor.getString(offset + 3)));
        entity.setStartDate(cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)));
        entity.setCloseDate(cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)));
        entity.setCostTime(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ImportantNode entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ImportantNode entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ImportantNode entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
