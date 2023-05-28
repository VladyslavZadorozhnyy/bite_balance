package com.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.database.common.Constants

@Entity(tableName = Constants.SETTING_TABLE_NAME)
data class SettingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "icon_res") val iconRes: Int,
    @ColumnInfo(name = "setting_text") val settingText: String
)