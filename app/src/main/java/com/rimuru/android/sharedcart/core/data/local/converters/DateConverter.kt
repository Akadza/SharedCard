package com.rimuru.android.sharedcart.core.data.local.converters

import androidx.room.TypeConverter
import java.time.Instant

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Instant? {
        return value?.let { Instant.ofEpochMilli(it) }
    }
    @TypeConverter
    fun dateToTimestamp(date: Instant?): Long? {
        return date?.toEpochMilli()
    }
}