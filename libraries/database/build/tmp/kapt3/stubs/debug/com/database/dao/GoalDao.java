package com.database.dao;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\'J\u0012\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\tH\'J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\tH\'\u00a8\u0006\u000e"}, d2 = {"Lcom/database/dao/GoalDao;", "", "deleteAll", "", "deleteById", "id", "", "getAll", "", "Lcom/database/entities/GoalEntity;", "getById", "insert", "goalEntity", "updateItem", "database_debug"})
public abstract interface GoalDao {
    
    @androidx.room.Insert()
    public abstract long insert(@org.jetbrains.annotations.NotNull()
    com.database.entities.GoalEntity goalEntity);
    
    @androidx.room.Update()
    public abstract void updateItem(@org.jetbrains.annotations.NotNull()
    com.database.entities.GoalEntity goalEntity);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM goal_table")
    public abstract java.util.List<com.database.entities.GoalEntity> getAll();
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "SELECT * FROM goal_table WHERE id=:id")
    public abstract com.database.entities.GoalEntity getById(long id);
    
    @androidx.room.Query(value = "DELETE FROM goal_table WHERE id=:id")
    public abstract void deleteById(long id);
    
    @androidx.room.Query(value = "DELETE FROM goal_table")
    public abstract void deleteAll();
}