package com.rimuru.android.sharedcart.data.local.converter

import androidx.room.TypeConverter
import java.time.Instant

class ProductConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Instant? {
        return value?.let { Instant.ofEpochMilli(it) }
    }
    @TypeConverter
    fun dateToTimestamp(date: Instant?): Long? {
        return date?.toEpochMilli()
    }
}