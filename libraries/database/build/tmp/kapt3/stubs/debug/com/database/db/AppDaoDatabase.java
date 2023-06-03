package com.database.db;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0013H&\u00a8\u0006\u0014"}, d2 = {"Lcom/database/db/AppDaoDatabase;", "", "getDateDao", "Lcom/database/dao/DateDao;", "getDishDao", "Lcom/database/dao/DishDao;", "getGoalDao", "Lcom/database/dao/GoalDao;", "getGoalMetricsDao", "Lcom/database/dao/GoalMetricsDao;", "getIconLegendDao", "Lcom/database/dao/IconLegendDao;", "getMealDao", "Lcom/database/dao/MealDao;", "getMetricDao", "Lcom/database/dao/MetricDao;", "getNutritionValueDao", "Lcom/database/dao/NutritionValueDao;", "getSettingDao", "Lcom/database/dao/SettingDao;", "database_debug"})
public abstract interface AppDaoDatabase {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.DateDao getDateDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.DishDao getDishDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.GoalDao getGoalDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.GoalMetricsDao getGoalMetricsDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.IconLegendDao getIconLegendDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.MealDao getMealDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.MetricDao getMetricDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.NutritionValueDao getNutritionValueDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.database.dao.SettingDao getSettingDao();
}