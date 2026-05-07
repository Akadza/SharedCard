package com.rimuru.android.sharedcart.di

import android.content.Context
import androidx.room.Room
import com.rimuru.android.sharedcart.data.local.SharedCartDatabase
import com.rimuru.android.sharedcart.data.local.dao.ProductDao
import com.rimuru.android.sharedcart.data.local.dao.ShoppingListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SharedCartDatabase {
        return Room.databaseBuilder(
            context,
            SharedCartDatabase::class.java,
            "shared_cart_db"
        ).build()
    }

    @Provides
    fun provideShoppingListDao(database: SharedCartDatabase): ShoppingListDao {
        return database.shoppingListDao()
    }

    @Provides
    fun provideProductDao(database: SharedCartDatabase): ProductDao {
        return database.productDao()
    }
}

