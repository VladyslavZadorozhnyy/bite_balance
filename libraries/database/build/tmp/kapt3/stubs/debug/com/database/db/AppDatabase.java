package com.database.db;

import java.lang.System;

@androidx.room.Database(entities = {com.database.entities.DateEntity.class, com.database.entities.DishEntity.class, com.database.entities.GoalEntity.class, com.database.entities.GoalMetricsEntity.class, com.database.entities.IconLegendEntity.class, com.database.entities.MealEntity.class, com.database.entities.MetricEntity.class, com.database.entities.NutritionValueEntity.class, com.database.entities.SettingEntity.class}, exportSchema = false, version = 5)
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0014H&\u00a8\u0006\u0015"}, d2 = {"Lcom/database/db/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "dateDao", "Lcom/database/dao/DateDao;", "dishDao", "Lcom/database/dao/DishDao;", "goalDao", "Lcom/database/dao/GoalDao;", "goalMetricsDao", "Lcom/database/dao/GoalMetricsDao;", "iconLegendDao", "Lcom/database/dao/IconLegendDao;", "mealDao", "Lcom/database/dao/MealDao;", "metricDao", "Lcom/database/dao/MetricDao;", "nutritionValueDao", "Lcom/database/dao/NutritionValueDao;", "settingDao", "Lcom/database/dao/SettingDao;", "database_debug"})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.DateDao dateDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.DishDao dishDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.GoalDao goalDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.GoalMetricsDao goalMetricsDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.IconLegendDao iconLegendDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.MealDao mealDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.MetricDao metricDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.NutritionValueDao nutritionValueDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.SettingDao settingDao();
}