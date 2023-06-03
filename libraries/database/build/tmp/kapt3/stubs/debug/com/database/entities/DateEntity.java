package com.database.entities;

import java.lang.System;

@androidx.room.Entity(tableName = "date_table")
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u000bH\u00c6\u0003JO\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00d6\u0001J\t\u0010#\u001a\u00020\tH\u00d6\u0001R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010\u00a8\u0006$"}, d2 = {"Lcom/database/entities/DateEntity;", "", "minute", "", "hour", "day", "month", "year", "additions", "", "id", "", "(IIIIILjava/lang/String;J)V", "getAdditions", "()Ljava/lang/String;", "getDay", "()I", "getHour", "getId", "()J", "getMinute", "getMonth", "getYear", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "database_debug"})
public final class DateEntity {
    @androidx.room.ColumnInfo(name = "minute")
    private final int minute = 0;
    @androidx.room.ColumnInfo(name = "hour")
    private final int hour = 0;
    @androidx.room.ColumnInfo(name = "day")
    private final int day = 0;
    @androidx.room.ColumnInfo(name = "month")
    private final int month = 0;
    @androidx.room.ColumnInfo(name = "year")
    private final int year = 0;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "additions")
    private final java.lang.String additions = null;
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    
    @org.jetbrains.annotations.NotNull()
    public final com.database.entities.DateEntity copy(int minute, int hour, int day, int month, int year, @org.jetbrains.annotations.NotNull()
    java.lang.String additions, long id) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public DateEntity(int minute, int hour, int day, int month, int year, @org.jetbrains.annotations.NotNull()
    java.lang.String additions, long id) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getMinute() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int getHour() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int getDay() {
        return 0;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int getMonth() {
        return 0;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int getYear() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAdditions() {
        return null;
    }
    
    public final long component7() {
        return 0L;
    }
    
    public final long getId() {
        return 0L;
    }
}