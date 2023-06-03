package com.database.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.database.dao.DateDao;
import com.database.dao.DateDao_Impl;
import com.database.dao.DishDao;
import com.database.dao.DishDao_Impl;
import com.database.dao.GoalDao;
import com.database.dao.GoalDao_Impl;
import com.database.dao.GoalMetricsDao;
import com.database.dao.GoalMetricsDao_Impl;
import com.database.dao.IconLegendDao;
import com.database.dao.IconLegendDao_Impl;
import com.database.dao.MealDao;
import com.database.dao.MealDao_Impl;
import com.database.dao.MetricDao;
import com.database.dao.MetricDao_Impl;
import com.database.dao.NutritionValueDao;
import com.database.dao.NutritionValueDao_Impl;
import com.database.dao.SettingDao;
import com.database.dao.SettingDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile DateDao _dateDao;

  private volatile DishDao _dishDao;

  private volatile GoalDao _goalDao;

  private volatile GoalMetricsDao _goalMetricsDao;

  private volatile IconLegendDao _iconLegendDao;

  private volatile MealDao _mealDao;

  private volatile MetricDao _metricDao;

  private volatile NutritionValueDao _nutritionValueDao;

  private volatile SettingDao _settingDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(5) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `date_table` (`minute` INTEGER NOT NULL, `hour` INTEGER NOT NULL, `day` INTEGER NOT NULL, `month` INTEGER NOT NULL, `year` INTEGER NOT NULL, `additions` TEXT NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `dish_table` (`name` TEXT NOT NULL, `icon_res` INTEGER NOT NULL, `nutrition_model_id` INTEGER NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `goal_table` (`text_value` TEXT NOT NULL, `active` INTEGER NOT NULL, `achieved` INTEGER NOT NULL, `date_created_id` INTEGER NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `goal_metrics_table` (`table_name` TEXT NOT NULL, `entity_id` INTEGER NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `icon_legend_table` (`icon_res` INTEGER NOT NULL, `instruction_text` TEXT NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `meal_table` (`meal_time_id` INTEGER NOT NULL, `amount` REAL NOT NULL, `nutrition_value_id` INTEGER NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `metric_table` (`name` TEXT NOT NULL, `hint` TEXT NOT NULL, `suffix` TEXT NOT NULL, `editable` INTEGER NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `nutrition_value_table` (`prots` REAL NOT NULL, `fats` REAL NOT NULL, `carbs` REAL NOT NULL, `kcals` REAL NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `setting_table` (`icon_res` INTEGER NOT NULL, `setting_text` TEXT NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'e294de24fcca5c515c9c5001942b9633')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `date_table`");
        _db.execSQL("DROP TABLE IF EXISTS `dish_table`");
        _db.execSQL("DROP TABLE IF EXISTS `goal_table`");
        _db.execSQL("DROP TABLE IF EXISTS `goal_metrics_table`");
        _db.execSQL("DROP TABLE IF EXISTS `icon_legend_table`");
        _db.execSQL("DROP TABLE IF EXISTS `meal_table`");
        _db.execSQL("DROP TABLE IF EXISTS `metric_table`");
        _db.execSQL("DROP TABLE IF EXISTS `nutrition_value_table`");
        _db.execSQL("DROP TABLE IF EXISTS `setting_table`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsDateTable = new HashMap<String, TableInfo.Column>(7);
        _columnsDateTable.put("minute", new TableInfo.Column("minute", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDateTable.put("hour", new TableInfo.Column("hour", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDateTable.put("day", new TableInfo.Column("day", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDateTable.put("month", new TableInfo.Column("month", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDateTable.put("year", new TableInfo.Column("year", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDateTable.put("additions", new TableInfo.Column("additions", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDateTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDateTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDateTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDateTable = new TableInfo("date_table", _columnsDateTable, _foreignKeysDateTable, _indicesDateTable);
        final TableInfo _existingDateTable = TableInfo.read(_db, "date_table");
        if (! _infoDateTable.equals(_existingDateTable)) {
          return new RoomOpenHelper.ValidationResult(false, "date_table(com.database.entities.DateEntity).\n"
                  + " Expected:\n" + _infoDateTable + "\n"
                  + " Found:\n" + _existingDateTable);
        }
        final HashMap<String, TableInfo.Column> _columnsDishTable = new HashMap<String, TableInfo.Column>(4);
        _columnsDishTable.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDishTable.put("icon_res", new TableInfo.Column("icon_res", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDishTable.put("nutrition_model_id", new TableInfo.Column("nutrition_model_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDishTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDishTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDishTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDishTable = new TableInfo("dish_table", _columnsDishTable, _foreignKeysDishTable, _indicesDishTable);
        final TableInfo _existingDishTable = TableInfo.read(_db, "dish_table");
        if (! _infoDishTable.equals(_existingDishTable)) {
          return new RoomOpenHelper.ValidationResult(false, "dish_table(com.database.entities.DishEntity).\n"
                  + " Expected:\n" + _infoDishTable + "\n"
                  + " Found:\n" + _existingDishTable);
        }
        final HashMap<String, TableInfo.Column> _columnsGoalTable = new HashMap<String, TableInfo.Column>(5);
        _columnsGoalTable.put("text_value", new TableInfo.Column("text_value", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoalTable.put("active", new TableInfo.Column("active", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoalTable.put("achieved", new TableInfo.Column("achieved", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoalTable.put("date_created_id", new TableInfo.Column("date_created_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoalTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGoalTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGoalTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGoalTable = new TableInfo("goal_table", _columnsGoalTable, _foreignKeysGoalTable, _indicesGoalTable);
        final TableInfo _existingGoalTable = TableInfo.read(_db, "goal_table");
        if (! _infoGoalTable.equals(_existingGoalTable)) {
          return new RoomOpenHelper.ValidationResult(false, "goal_table(com.database.entities.GoalEntity).\n"
                  + " Expected:\n" + _infoGoalTable + "\n"
                  + " Found:\n" + _existingGoalTable);
        }
        final HashMap<String, TableInfo.Column> _columnsGoalMetricsTable = new HashMap<String, TableInfo.Column>(3);
        _columnsGoalMetricsTable.put("table_name", new TableInfo.Column("table_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoalMetricsTable.put("entity_id", new TableInfo.Column("entity_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGoalMetricsTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGoalMetricsTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGoalMetricsTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGoalMetricsTable = new TableInfo("goal_metrics_table", _columnsGoalMetricsTable, _foreignKeysGoalMetricsTable, _indicesGoalMetricsTable);
        final TableInfo _existingGoalMetricsTable = TableInfo.read(_db, "goal_metrics_table");
        if (! _infoGoalMetricsTable.equals(_existingGoalMetricsTable)) {
          return new RoomOpenHelper.ValidationResult(false, "goal_metrics_table(com.database.entities.GoalMetricsEntity).\n"
                  + " Expected:\n" + _infoGoalMetricsTable + "\n"
                  + " Found:\n" + _existingGoalMetricsTable);
        }
        final HashMap<String, TableInfo.Column> _columnsIconLegendTable = new HashMap<String, TableInfo.Column>(3);
        _columnsIconLegendTable.put("icon_res", new TableInfo.Column("icon_res", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIconLegendTable.put("instruction_text", new TableInfo.Column("instruction_text", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIconLegendTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysIconLegendTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesIconLegendTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoIconLegendTable = new TableInfo("icon_legend_table", _columnsIconLegendTable, _foreignKeysIconLegendTable, _indicesIconLegendTable);
        final TableInfo _existingIconLegendTable = TableInfo.read(_db, "icon_legend_table");
        if (! _infoIconLegendTable.equals(_existingIconLegendTable)) {
          return new RoomOpenHelper.ValidationResult(false, "icon_legend_table(com.database.entities.IconLegendEntity).\n"
                  + " Expected:\n" + _infoIconLegendTable + "\n"
                  + " Found:\n" + _existingIconLegendTable);
        }
        final HashMap<String, TableInfo.Column> _columnsMealTable = new HashMap<String, TableInfo.Column>(4);
        _columnsMealTable.put("meal_time_id", new TableInfo.Column("meal_time_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMealTable.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMealTable.put("nutrition_value_id", new TableInfo.Column("nutrition_value_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMealTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMealTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMealTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMealTable = new TableInfo("meal_table", _columnsMealTable, _foreignKeysMealTable, _indicesMealTable);
        final TableInfo _existingMealTable = TableInfo.read(_db, "meal_table");
        if (! _infoMealTable.equals(_existingMealTable)) {
          return new RoomOpenHelper.ValidationResult(false, "meal_table(com.database.entities.MealEntity).\n"
                  + " Expected:\n" + _infoMealTable + "\n"
                  + " Found:\n" + _existingMealTable);
        }
        final HashMap<String, TableInfo.Column> _columnsMetricTable = new HashMap<String, TableInfo.Column>(5);
        _columnsMetricTable.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMetricTable.put("hint", new TableInfo.Column("hint", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMetricTable.put("suffix", new TableInfo.Column("suffix", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMetricTable.put("editable", new TableInfo.Column("editable", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMetricTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMetricTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMetricTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMetricTable = new TableInfo("metric_table", _columnsMetricTable, _foreignKeysMetricTable, _indicesMetricTable);
        final TableInfo _existingMetricTable = TableInfo.read(_db, "metric_table");
        if (! _infoMetricTable.equals(_existingMetricTable)) {
          return new RoomOpenHelper.ValidationResult(false, "metric_table(com.database.entities.MetricEntity).\n"
                  + " Expected:\n" + _infoMetricTable + "\n"
                  + " Found:\n" + _existingMetricTable);
        }
        final HashMap<String, TableInfo.Column> _columnsNutritionValueTable = new HashMap<String, TableInfo.Column>(5);
        _columnsNutritionValueTable.put("prots", new TableInfo.Column("prots", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNutritionValueTable.put("fats", new TableInfo.Column("fats", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNutritionValueTable.put("carbs", new TableInfo.Column("carbs", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNutritionValueTable.put("kcals", new TableInfo.Column("kcals", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNutritionValueTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNutritionValueTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNutritionValueTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNutritionValueTable = new TableInfo("nutrition_value_table", _columnsNutritionValueTable, _foreignKeysNutritionValueTable, _indicesNutritionValueTable);
        final TableInfo _existingNutritionValueTable = TableInfo.read(_db, "nutrition_value_table");
        if (! _infoNutritionValueTable.equals(_existingNutritionValueTable)) {
          return new RoomOpenHelper.ValidationResult(false, "nutrition_value_table(com.database.entities.NutritionValueEntity).\n"
                  + " Expected:\n" + _infoNutritionValueTable + "\n"
                  + " Found:\n" + _existingNutritionValueTable);
        }
        final HashMap<String, TableInfo.Column> _columnsSettingTable = new HashMap<String, TableInfo.Column>(3);
        _columnsSettingTable.put("icon_res", new TableInfo.Column("icon_res", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSettingTable.put("setting_text", new TableInfo.Column("setting_text", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSettingTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSettingTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSettingTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSettingTable = new TableInfo("setting_table", _columnsSettingTable, _foreignKeysSettingTable, _indicesSettingTable);
        final TableInfo _existingSettingTable = TableInfo.read(_db, "setting_table");
        if (! _infoSettingTable.equals(_existingSettingTable)) {
          return new RoomOpenHelper.ValidationResult(false, "setting_table(com.database.entities.SettingEntity).\n"
                  + " Expected:\n" + _infoSettingTable + "\n"
                  + " Found:\n" + _existingSettingTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "e294de24fcca5c515c9c5001942b9633", "5424c090041944db824af78e357fe5d6");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "date_table","dish_table","goal_table","goal_metrics_table","icon_legend_table","meal_table","metric_table","nutrition_value_table","setting_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `date_table`");
      _db.execSQL("DELETE FROM `dish_table`");
      _db.execSQL("DELETE FROM `goal_table`");
      _db.execSQL("DELETE FROM `goal_metrics_table`");
      _db.execSQL("DELETE FROM `icon_legend_table`");
      _db.execSQL("DELETE FROM `meal_table`");
      _db.execSQL("DELETE FROM `metric_table`");
      _db.execSQL("DELETE FROM `nutrition_value_table`");
      _db.execSQL("DELETE FROM `setting_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(DateDao.class, DateDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DishDao.class, DishDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GoalDao.class, GoalDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GoalMetricsDao.class, GoalMetricsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(IconLegendDao.class, IconLegendDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MealDao.class, MealDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MetricDao.class, MetricDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(NutritionValueDao.class, NutritionValueDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SettingDao.class, SettingDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public DateDao dateDao() {
    if (_dateDao != null) {
      return _dateDao;
    } else {
      synchronized(this) {
        if(_dateDao == null) {
          _dateDao = new DateDao_Impl(this);
        }
        return _dateDao;
      }
    }
  }

  @Override
  public DishDao dishDao() {
    if (_dishDao != null) {
      return _dishDao;
    } else {
      synchronized(this) {
        if(_dishDao == null) {
          _dishDao = new DishDao_Impl(this);
        }
        return _dishDao;
      }
    }
  }

  @Override
  public GoalDao goalDao() {
    if (_goalDao != null) {
      return _goalDao;
    } else {
      synchronized(this) {
        if(_goalDao == null) {
          _goalDao = new GoalDao_Impl(this);
        }
        return _goalDao;
      }
    }
  }

  @Override
  public GoalMetricsDao goalMetricsDao() {
    if (_goalMetricsDao != null) {
      return _goalMetricsDao;
    } else {
      synchronized(this) {
        if(_goalMetricsDao == null) {
          _goalMetricsDao = new GoalMetricsDao_Impl(this);
        }
        return _goalMetricsDao;
      }
    }
  }

  @Override
  public IconLegendDao iconLegendDao() {
    if (_iconLegendDao != null) {
      return _iconLegendDao;
    } else {
      synchronized(this) {
        if(_iconLegendDao == null) {
          _iconLegendDao = new IconLegendDao_Impl(this);
        }
        return _iconLegendDao;
      }
    }
  }

  @Override
  public MealDao mealDao() {
    if (_mealDao != null) {
      return _mealDao;
    } else {
      synchronized(this) {
        if(_mealDao == null) {
          _mealDao = new MealDao_Impl(this);
        }
        return _mealDao;
      }
    }
  }

  @Override
  public MetricDao metricDao() {
    if (_metricDao != null) {
      return _metricDao;
    } else {
      synchronized(this) {
        if(_metricDao == null) {
          _metricDao = new MetricDao_Impl(this);
        }
        return _metricDao;
      }
    }
  }

  @Override
  public NutritionValueDao nutritionValueDao() {
    if (_nutritionValueDao != null) {
      return _nutritionValueDao;
    } else {
      synchronized(this) {
        if(_nutritionValueDao == null) {
          _nutritionValueDao = new NutritionValueDao_Impl(this);
        }
        return _nutritionValueDao;
      }
    }
  }

  @Override
  public SettingDao settingDao() {
    if (_settingDao != null) {
      return _settingDao;
    } else {
      synchronized(this) {
        if(_settingDao == null) {
          _settingDao = new SettingDao_Impl(this);
        }
        return _settingDao;
      }
    }
  }
}
