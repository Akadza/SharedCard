package com.rimuru.android.sharedcart.di

import com.rimuru.android.sharedcart.feature.shopping_list.data.repository.ShoppingRepositoryImpl
import com.rimuru.android.sharedcart.feature.shopping_list.domain.repository.ShoppingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindShoppingRepository(
        impl: ShoppingRepositoryImpl
    ): ShoppingRepository
}