package com.example.myapplication.database.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = BufferEntity.NAME,
        indices = {@Index(BufferEntity.VERSION)}
)
public class BufferEntity {
    static final String NAME="Buffer";
    static final String IS_EMPTY="Is_empty";

    static final String VERSION="ID";

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = VERSION)
    private final Integer id;

    @ColumnInfo(name = IS_EMPTY)
    private final Boolean is_empty;

    public BufferEntity(@NonNull Integer id, Boolean is_empty) {
        this.id = id;
        this.is_empty = is_empty;
    }


    public Boolean getIs_empty() {
        return is_empty;
    }

    @NonNull
    public Integer getId() {
        return id;
    }
}
