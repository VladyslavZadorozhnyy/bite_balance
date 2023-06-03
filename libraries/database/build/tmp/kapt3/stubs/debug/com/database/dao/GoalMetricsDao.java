package com.database.dao;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\'J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\'J\u0012\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\b\u001a\u00020\tH\'J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\fH\'J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\'\u00a8\u0006\u0012"}, d2 = {"Lcom/database/dao/GoalMetricsDao;", "", "deleteAll", "", "deleteById", "id", "", "deleteByTableName", "tableName", "", "getAll", "", "Lcom/database/entities/GoalMetricsEntity;", "getByTableName", "insert", "goalMetricsEntity", "updateItem", "newEntityId", "database_debug"})
public abstract interface GoalMetricsDao {
    
    @androidx.room.Insert()
    public abstract long insert(@org.jetbrains.annotations.NotNull()
    com.database.entities.GoalMetricsEntity goalMetricsEntity);
    
    @androidx.room.Query(value = "UPDATE goal_metrics_table SET entity_id=:newEntityId WHERE table_name=:tableName")
    public abstract void updateItem(long newEntityId, @org.jetbrains.annotations.NotNull()
    java.lang.String tableName);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM goal_metrics_table")
    public abstract java.util.List<com.database.entities.GoalMetricsEntity> getAll();
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "SELECT * FROM goal_metrics_table WHERE table_name=:tableName")
    public abstract com.database.entities.GoalMetricsEntity getByTableName(@org.jetbrains.annotations.NotNull()
    java.lang.String tableName);
    
    @androidx.room.Query(value = "DELETE FROM goal_metrics_table WHERE id=:id")
    public abstract void deleteById(long id);
    
    @androidx.room.Query(value = "DELETE FROM goal_metrics_table WHERE table_name=:tableName")
    public abstract void deleteByTableName(@org.jetbrains.annotations.NotNull()
    java.lang.String tableName);
    
    @androidx.room.Query(value = "DELETE FROM goal_metrics_table")
    public abstract void deleteAll();
}