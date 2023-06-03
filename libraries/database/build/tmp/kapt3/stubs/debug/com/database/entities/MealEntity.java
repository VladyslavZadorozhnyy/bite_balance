package com.database.entities;

import java.lang.System;

@androidx.room.Entity(tableName = "meal_table")
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u0006\u001b"}, d2 = {"Lcom/database/entities/MealEntity;", "", "mealTimeId", "", "amount", "", "nutritionValueId", "id", "(JFJJ)V", "getAmount", "()F", "getId", "()J", "getMealTimeId", "getNutritionValueId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "database_debug"})
public final class MealEntity {
    @androidx.room.ColumnInfo(name = "meal_time_id")
    private final long mealTimeId = 0L;
    @androidx.room.ColumnInfo(name = "amount")
    private final float amount = 0.0F;
    @androidx.room.ColumnInfo(name = "nutrition_value_id")
    private final long nutritionValueId = 0L;
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    
    @org.jetbrains.annotations.NotNull()
    public final com.database.entities.MealEntity copy(long mealTimeId, float amount, long nutritionValueId, long id) {
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
    
    public MealEntity(long mealTimeId, float amount, long nutritionValueId, long id) {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long getMealTimeId() {
        return 0L;
    }
    
    public final float component2() {
        return 0.0F;
    }
    
    public final float getAmount() {
        return 0.0F;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final long getNutritionValueId() {
        return 0L;
    }
    
    public final long component4() {
        return 0L;
    }
    
    public final long getId() {
        return 0L;
    }
}