package com.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.database.common.Constants

@Entity(tableName = Constants.SETTING_TABLE_NAME)
data class SettingEntity(
    @ColumnInfo(name = "icon_res") val iconRes: Int,
    @ColumnInfo(name = "setting_text") val settingText: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)