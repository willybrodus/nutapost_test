package com.nutapos.nutatest.core.local.di

import android.content.Context
import androidx.room.Room
import com.nutapos.nutatest.core.local.AppDatabase
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
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "sample-app.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideCustomerDao(appDatabase: AppDatabase) = appDatabase.customerDao()

    @Provides
    fun provideCashInflowDao(appDatabase: AppDatabase) = appDatabase.cashInflowDao()

    @Provides
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()

    @Provides
    fun provideCashOutDao(appDatabase: AppDatabase) = appDatabase.cashOutDao()
}
